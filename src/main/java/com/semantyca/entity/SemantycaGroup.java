package com.semantyca.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.semantyca.traits.Identity;

@Data
@Entity
@Table(name="SEM_GROUPS", schema = "PUBLIC")
@NamedQuery(name="SemantycaGroup.findAll", query="SELECT c FROM SemantycaGroup c WHERE 1=1")
public class SemantycaGroup implements Identity {

    @Id
    private String id;

    @NotNull
    @Column(name="group_name")
    private String groupName;

    public SemantycaGroup() {
        this.id = UUID.randomUUID().toString();
    }
}
