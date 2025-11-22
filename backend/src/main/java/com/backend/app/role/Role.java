package com.backend.app.role;

import java.util.UUID;

import com.backend.app.common.converter.MapConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends MapConverter{
       @Id
       @GeneratedValue(strategy = GenerationType.UUID)
       private UUID id;

       @Column(unique = true, nullable = false)
       private String name;
}
