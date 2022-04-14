package com.learn.universityjpa.service.excel;

import com.learn.universityjpa.annotations.SqlTest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@PropertySource("classpath:values.properties")
class WriteDataToExcelImplTest {
    @Value("${spring.dir.excel}")
    private String fileLocation;

    @Autowired
    private WriteDataToExcelImpl writeDataToExcelImpl;

    @DisplayName("Проверка создания excel файла")
    @SqlTest
    void writeGroupsToFile() throws IOException, InvalidFormatException {
        String path = new File("").getAbsolutePath() + fileLocation;

        boolean result = Files.deleteIfExists(new File(path).toPath());

        writeDataToExcelImpl.create();
        File f = new File(path);

        assertTrue(f.exists());
        FileInputStream inputStream = new FileInputStream(f);

        OPCPackage pkg = OPCPackage.open(f);
        XSSFWorkbook workbook = new XSSFWorkbook(pkg);

        XSSFSheet sheet0 = workbook.getSheetAt(0);
        Row row0 = sheet0.getRow(0);
        assertEquals(row0.getCell(0).getStringCellValue(), "№");
        assertEquals(row0.getCell(1).getStringCellValue(), "Name");
        assertEquals(row0.getCell(2).getStringCellValue(), "Specification");

        XSSFSheet sheet1 = workbook.getSheetAt(1);
        Row row01 = sheet1.getRow(0);
        assertEquals(row01.getCell(0).getStringCellValue(), "№");
        assertEquals(row01.getCell(1).getStringCellValue(), "Name");
        assertEquals(row01.getCell(2).getStringCellValue(), "Description");

        XSSFSheet sheet2 = workbook.getSheetAt(2);
        Row row02 = sheet2.getRow(0);
        assertEquals(row02.getCell(0).getStringCellValue(), "№");
        assertEquals(row02.getCell(1).getStringCellValue(), "FirstName");
        assertEquals(row02.getCell(2).getStringCellValue(), "SecondName");
        assertEquals(row02.getCell(3).getStringCellValue(), "LastName");
        assertEquals(row02.getCell(4).getStringCellValue(), "DateBirth");
        assertEquals(row02.getCell(5).getStringCellValue(), "Gender");
        assertEquals(row02.getCell(6).getStringCellValue(), "Group");

        XSSFSheet sheet3 = workbook.getSheetAt(3);
        Row row03 = sheet3.getRow(0);
        assertEquals(row03.getCell(0).getStringCellValue(), "№");
        assertEquals(row03.getCell(1).getStringCellValue(), "FirstName");
        assertEquals(row03.getCell(2).getStringCellValue(), "SecondName");
        assertEquals(row03.getCell(3).getStringCellValue(), "LastName");
        assertEquals(row03.getCell(4).getStringCellValue(), "DateBirth");
        assertEquals(row03.getCell(5).getStringCellValue(), "Gender");
        assertEquals(row03.getCell(6).getStringCellValue(), "Category");
    }
}
