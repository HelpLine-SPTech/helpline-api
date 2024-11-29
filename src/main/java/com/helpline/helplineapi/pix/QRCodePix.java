package com.helpline.helplineapi.pix;/*
 * Classe baseada em uma biblioteca PHP disponível em https://github.com/renatomb/php_qrcode_pix.
*/

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.Base64;
import java.util.EnumMap;

import static java.lang.Integer.toHexString;

/**
 * Gera um QRCode para fazer transferências PIX "Copia e Cola".
 * @see DadosEnvioPix
 * @author Manoel Campos da Silva Filho
 * @see #generate()
 */
public final class QRCodePix {
    /**
     * Payload Format Indicator. Código 00 com valor fixo 01 (obrigatório)
     */
    private static final String PFI = "01";

    /**
     * Código para identificar o campo com o checksum do QRCode gerado.
     * @see #crcChecksum(String)
     */
    public static final String COD_CRC = "6304";

    /**
     * Código de país no formato ISO3166-1 alpha 2
     */
    private static final String COD_PAIS = "BR";

    /**
     * Moeda, "986" = BRL: Real Brasileiro - ISO4217
     */
    private static final String COD_MOEDA = "986";

    /**
     * Arranjo específico; Código "00" (GUI) obrigatório e valor fixo: br.gov.bcb.pix
     */
    private static final String ARRANJO_PAGAMENTO = "BR.GOV.BCB.PIX";

    /**
     * Merchant Category Code (MCC ISO18245)
     */
    private static final String MCC = "0000";

    /**
     * Código do campo que armazena o valor do PIX.
     */
    private static final String COD_CAMPO_VALOR = "54";

    /**
     * Valor para o idTransacao quando o campo não for informado.
     */
    private static final String ID_TRANSACAO_VAZIO = "***";

    private final DadosEnvioPix dadosPix;

    /**
     * Cria um objeto pix.QRCodePix sem um id da transação
     * @param dadosPix Dados preenchidos pelo usuário para envio do PIX
     */
    public QRCodePix(final DadosEnvioPix dadosPix) {
        this.dadosPix = dadosPix;
    }

    /**
     * Cria um objeto JSON contendo os dados completos para gerar o QRCode.
     *
     * @return o objeto JSON criado
     * @see #generate()
     */
    private JSONObject newJSONObject() {
        final var jsonTemplate =
            """
            {
                '00': '%s',
                '26': {
                    '00': '%s',
                    '01': '%s',
                    '02': '%s'
                },
                '52': '%s',
                '53': '%s',
                '%s': '%s',
                '58': '%s',
                '59': '%s',
                '60': '%s',
                '62': {
                    '05': '%s'
                }
            }
            """;

        final var json =
            jsonTemplate
                .formatted(
                        PFI, ARRANJO_PAGAMENTO, dadosPix.chaveDestinatario(), dadosPix.descricao(),
                        MCC, COD_MOEDA, COD_CAMPO_VALOR, dadosPix.valorStr(), COD_PAIS,
                        dadosPix.nomeDestinatario(), dadosPix.cidadeRemetente(), ID_TRANSACAO_VAZIO);
        return new JSONObject(json);
    }

    /**
     * Gera o QRCode PIX "Copia e Cola" para os dados informados.
     * @return o código gerado
     * @see #toString()
     */
    public String generate() {
        final String partialCode = generateInternal(newJSONObject()) + COD_CRC;
        final String checksum = crcChecksum(partialCode);
        return partialCode + checksum;
    }

    private String generateInternal(final JSONObject jsonObj) {
        final var sb = new StringBuilder();
        jsonObj.keySet().stream().sorted().forEach(key -> {
            final Object val = jsonObj.get(key);
            final String str = encodeValue(key, val);
            sb.append(leftPad(key)).append(strLenLeftPadded(str)).append(str);
        });

        return sb.toString();
    }

    /**
     * Codifica o valor de uma chave do objeto JSON com configurações
     * para geração do QRCode, conforme as especificações do PIX.
     * @param key nome da chave no objeto JSON contendo parte dos dados
     * @param val valor para a chave correspondente no objeto JSON
     * @return o valor da chave codificado
     */
    private String encodeValue(final String key, final Object val) {
        //Se o valor para a chave contém outro objeto, processa seus atributos recursivamente
        if(val instanceof JSONObject jsonObjValue)
            return generateInternal(jsonObjValue);

        //Se o valor é String ou um tipo primitivo
        return key.equals(COD_CAMPO_VALOR) ? val.toString() : removeSpecialChars(val);
    }

    /**
     * Calcula o checksum CRC16 a partir de um código parcial do PIX.
     * @param partialCode código parcial do QRCode
     * @return o checksum em hexadecimal
     */
    private String crcChecksum(final String partialCode){
        int crc = 0xFFFF;
        final var byteArray = partialCode.getBytes();
        for (final byte b : byteArray) {
            crc ^= b << 8;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) == 0)
                    crc = crc << 1;
                else crc = (crc << 1) ^ 0x1021;
            }
        }

        final int decimal = crc & 0xFFFF;
        return leftPad(toHexString(decimal), 4).toUpperCase();
    }

    private String removeSpecialChars(final Object value) {
        return value.toString().replaceAll("[^a-zA-Z0-9\\-@\\.\\*\\s\\+]", "");
    }

    /**
     * Obtém o total de caracteres de uma String incluindo zero a esquerda se necessário.
     * @return o total como uma String de dois dígitos (incluindo zero à esquerda se necessário).
     * @throws IllegalArgumentException se a quantidade de caracteres do valor é maior que o permitido
    */
    static String strLenLeftPadded(final String value) {
        if (value.length() > 99) {
            final var msg = "Tamanho máximo dos valores dos campos deve ser 99. '%s' tem %d caracteres.".formatted(value, value.length());
            throw new IllegalArgumentException(msg);
        }

        final String len = String.valueOf(value.length());
        return leftPad(len);
    }

    /**
     * Inclui zero à esquerda de um código de um campo do QRCode PIX (se necessário),
     * pois todos os códigos devem ter 2 dígitos.
     * @param code código de um campo do QRCode PIX
     * @return o código com um possível zero à esquerda
     */
    private static String leftPad(final String code) {
        return leftPad(code, 2);
    }

    /**
     * Inclui uma determinada quantidade de zeros à esquerda de um valor.
     * @param code código pra incluir zeros à esquerda
     * @param len tamanho máximo da String retornada
     * @return o código com possíveis zeros à esquerda
     */
    private static String leftPad(final String code, final int len) {
        final var format = "%1$" + len + "s";
        return format.formatted(code).replace(' ', '0');
    }



    /**
     * Salva o QRCode gerado com {@link #generate()} em um arquivo de imagem temporário com nome aleatório.
     * Se o código não foi gerado ainda, chama automaticamente o {@link #generate()}.
     * @return o caminho do arquivo gerado
     */
    public QrCodeInfo save() {
        return saveAndGetBytes();
    }

    /**
     * Salva o QRCode gerado com {@link #generate()} em um arquivo de imagem.
     * Se o código não foi gerado ainda, chama automaticamente o {@link #generate()}.
     * @return um vetor de bytes representando a imagem gerada
     * @throws IOException se não for possível acessar o arquivo para gravação
     * @throws WriterException se ocorrer erro durante a gravação de dados no arquivo
     * @see #save()
     */
    public QrCodeInfo saveAndGetBytes() {
        //Obtém a extensão do arquivo
        final var fileFormat = "png";

        final var hintsMap = new EnumMap<>(EncodeHintType.class);
        hintsMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintsMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        final int tamanho = 300; // Tamanho da imagem do QRCode em pixels

        final var writer = new QRCodeWriter();
        try {
            String code = generate();

            final var bitMatrix = writer.encode(code, BarcodeFormat.QR_CODE, tamanho, tamanho, hintsMap);
            final var image = new BufferedImage(tamanho, tamanho, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < tamanho; y++) {
                for (int x = 0; x < tamanho; x++) {
                    final var isBlack = bitMatrix.get(x, y);
                    final int color = isBlack ? 0 : 0xFFFFFF; //black or white
                    image.setRGB(x, y, color);
                }
            }

            final var baos = new ByteArrayOutputStream();
            ImageIO.write(image, fileFormat, baos);
            final var byteArray = baos.toByteArray();
            return new QrCodeInfo(byteArray, code);
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }

    @AllArgsConstructor
    public static class QrCodeInfo {
        public byte[] qrCode;

        public String code;
    }
}
