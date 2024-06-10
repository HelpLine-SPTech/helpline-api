package com.helpline.helplineapi.entities.campaing;

import com.helpline.helplineapi.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "campaign")
@Entity(name = "campaign")
public class Campaign extends BaseEntity {
}
