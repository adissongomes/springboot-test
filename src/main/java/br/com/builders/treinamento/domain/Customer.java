package br.com.builders.treinamento.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@Document(collection = "adissongomes_customers")
public class Customer {

    @Id
    private String id;
    private String crmId;
    @URL
    private String baseUrl;
    private String name;
    @Email
    @Indexed(unique = true)
    private String login;

}
