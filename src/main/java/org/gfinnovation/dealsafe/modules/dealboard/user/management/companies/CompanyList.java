package org.gfinnovation.dealsafe.modules.dealboard.user.management.companies;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.modules.domain.GenericBusinessClass;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CompanyList extends GenericBusinessClass {
    private String name;
    private List<String> cnpjs;

    public CompanyList(List<String> cnpjs) {
        this.cnpjs = cnpjs;
    }

    public void addCnpj(String cnpj) {
        this.cnpjs.add(cnpj);
    }

    public void removeCnpj(String cnpj) {
        this.cnpjs.remove(cnpj);
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
