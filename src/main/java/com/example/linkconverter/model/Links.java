package com.example.linkconverter.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "links")
@Entity
public class Links {
    @GeneratedValue
    @Id
    int id;
    String webUrl;
    String deeplink;
    LocalDateTime createDate;
}
