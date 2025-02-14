package org.gfinnovation.dealsafe.tests.service;

import jakarta.transaction.Transactional;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.adapter.web.request.CompanyListData;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.application.service.interfaces.CompanyListService;
import org.gfinnovation.dealsafe.modules.dealboard.user.management.companies.domain.CompanyList;
import org.gfinnovation.dealsafe.tests._shared.GenericTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class CompanyCustomListServiceTest
 * @since v1.0 (10/02/2025)
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: CompanyCustomListService")
public class CompanyCustomListServiceTest extends GenericTest {
    @Autowired
    private CompanyListService companyListService;


    @Test
    @Transactional
    public void testCreate(){
        String listName = "test";
        List<String> list = new ArrayList<>(List.of("54913262000176", "38676795000125", "79225338000100"));

        CompanyList companyList = this.companyListService.createCompanyList(listName, list);
        assertEquals(listName, companyList.getName());
        assertEquals(list, companyList.getCnpjs());
        assertNotNull(companyList.getId());
    }

    @Test
    @Transactional
    public void testUpdate(){
        String listName1 = "test 1";
        List<String> list1 = new ArrayList<>(List.of("54913262000176", "38676795000125", "79225338000100"));

        CompanyList companyList = this.companyListService.createCompanyList(listName1, list1);

        assertEquals(listName1, companyList.getName());
        assertEquals(list1, companyList.getCnpjs());
        assertNotNull(companyList.getId());

        String listName2 = "test 2";
        List<String> list2 = new ArrayList<>(List.of("54913262000177", "38676795000126", "79225338000101"));

        companyList = this.companyListService.updateCompanyList(companyList.getId(), new CompanyListData(listName2, list2));

        assertEquals(listName2, companyList.getName());
        assertEquals(list2, companyList.getCnpjs());
        assertNotNull(companyList.getId());
    }

    @Test
    @Transactional
    public void testUpdate2(){
        String listName1 = "test 1";
        List<String> list1 = new ArrayList<>(List.of("54913262000176", "38676795000125", "79225338000100"));

        CompanyList companyList = this.companyListService.createCompanyList(listName1, list1);

        assertEquals(listName1, companyList.getName());
        assertEquals(list1, companyList.getCnpjs());
        assertNotNull(companyList.getId());
        List<String> list2 = new ArrayList<>(List.of("54913262000177", "38676795000126", "79225338000101"));

        companyList = this.companyListService.updateCompanyList(companyList.getId(), new CompanyListData(null, list2));

        assertEquals(listName1, companyList.getName());
        assertEquals(list2, companyList.getCnpjs());
        assertNotNull(companyList.getId());
    }
}
