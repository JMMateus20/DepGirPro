package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="personas")
public class Persona implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona")
    private Long id;

    private String nombre;
    private Integer edad;
    private Integer peso;
    private Float altura;
    private String correo;
    @Column(name="contrasena")
    private String password;
    private Integer dorsal;
    private String urlFoto;
    @CreationTimestamp
    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;
    @UpdateTimestamp
    @Column(name="fecha_modificacion")
    private LocalDateTime fechaModificacion;
    private boolean enabled=false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="rol_id")
    private Rol role;

    public Persona(String nombre, Integer edad, Integer peso, Float altura, String correo, String password, Integer dorsal, Equipo equipo ,Rol rol) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.correo = correo;
        this.password = password;
        this.dorsal = dorsal;
        this.enabled=true;
        this.equipo=equipo;
        this.role=rol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.getRole().getRol()));
        this.role.getPermisos().forEach(permiso->{
            authorities.add(new SimpleGrantedAuthority(permiso.getOperacion().getNombre()));
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
