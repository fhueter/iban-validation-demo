package de.fhueter.demo.ibanvalidator.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.jspecify.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

@SuperBuilder
@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Nullable
	protected Long id;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		BaseEntity that = (BaseEntity) o;
		return id != null && Objects.equals(id, that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
