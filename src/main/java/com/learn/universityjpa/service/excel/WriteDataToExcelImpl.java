package com.learn.universityjpa.service.excel;

import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;
import com.learn.universityjpa.repo.GroupComponent;
import com.learn.universityjpa.repo.StudentComponent;
import com.learn.universityjpa.repo.SubjectComponent;
import com.learn.universityjpa.repo.TeacherComponent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class WriteDataToExcelImpl
 */

@Component
@PropertySource("classpath:values.properties")
public class WriteDataToExcelImpl implements WriteDataToExcel {

    @Value("${spring.dir.excel}")
    private String filePath;
  //  @Autowired
    private final GroupComponent groupComponent;
 //   @Autowired
    private final SubjectComponent subjectComponent;
   // @Autowired
    private final StudentComponent studentComponent;
    //@Autowired
    private final TeacherComponent teacherComponent;

    private final Workbook workbook = new XSSFWorkbook();
    private final CellStyle headerStyle;
    private final CellStyle cellStyle;
    File currDir;

    FileOutputStream outputStream;
    @Autowired
    public WriteDataToExcelImpl(GroupComponent groupComponent,
                                SubjectComponent subjectComponent,
                                StudentComponent studentComponent,
                                TeacherComponent teacherComponent){
        this.groupComponent = groupComponent;
        this.subjectComponent = subjectComponent;
        this.studentComponent = studentComponent;
        this.teacherComponent = teacherComponent;
        this.headerStyle = initHeaderStyle();
        this.cellStyle = initCellStyle();
    }

    private CellStyle initHeaderStyle(){
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(initFHeaderFont());
        return headerStyle;
    }

    private XSSFFont initFHeaderFont(){
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        return font;
    }

    private CellStyle initCellStyle(){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setFont(initFontRow());
        return cellStyle;
    }

    private XSSFFont initFontRow(){
        XSSFFont fontRow = ((XSSFWorkbook) workbook).createFont();
        fontRow.setFontName("Arial");
        fontRow.setFontHeightInPoints((short) 10);
        fontRow.setBold(true);
        return fontRow;
    }

    private void writeGroupsToFile() {
        String [] headers = { "№", "Name", "Specification" };

        Sheet sheet = workbook.createSheet("Groups");
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 10000);
        sheet.setColumnWidth(2, 24000);
        Row header = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerStyle);
        }

        List<Group> groups = groupComponent.findAll();
        for (int i = 0; i < groups.size(); i++) {
            Row row = sheet.createRow(i + 1);

            Cell rowCell0 = row.createCell(0);
            rowCell0.setCellValue(groups.get(i).getId());
            rowCell0.setCellStyle(cellStyle);

            Cell rowCell1 = row.createCell(1);
            rowCell1.setCellValue(groups.get(i).getName());
            rowCell1.setCellStyle(cellStyle);

            Cell rowCell2 = row.createCell(2);
            rowCell2.setCellValue(groups.get(i).getSpecification());
            rowCell2.setCellStyle(cellStyle);
        }
    }

    private void writeSubjectsToFile() {
        String [] headers = { "№", "Name", "Description" };

        Sheet sheet = workbook.createSheet("Subjects");
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 10000);
        sheet.setColumnWidth(2, 24000);
        Row header = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerStyle);
        }

        List<Subject> subjects = subjectComponent.findAll();
        for (int i = 0; i < subjects.size(); i++) {
            Row row = sheet.createRow(i + 1);

            Cell rowCell0 = row.createCell(0);
            rowCell0.setCellValue(subjects.get(i).getId());
            rowCell0.setCellStyle(cellStyle);

            Cell rowCell1 = row.createCell(1);
            rowCell1.setCellValue(subjects.get(i).getName());
            rowCell1.setCellStyle(cellStyle);

            Cell rowCell2 = row.createCell(2);
            rowCell2.setCellValue(subjects.get(i).getDescription());
            rowCell2.setCellStyle(cellStyle);
        }
    }

    private void writeStudentsToFile() {
        String [] headers = { "№", "FirstName", "SecondName", "LastName", "DateBirth", "Gender" , "Group"};

        Sheet sheet = workbook.createSheet("Students");
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 5000);

        Row header = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerStyle);
        }

        List<Student> students = studentComponent.findAll();
        for (int i = 0; i < students.size(); i++) {
            Row row = sheet.createRow(i + 1);

            Cell rowCell0 = row.createCell(0);
            rowCell0.setCellValue(students.get(i).getId());
            rowCell0.setCellStyle(cellStyle);

            Cell rowCell1 = row.createCell(1);
            rowCell1.setCellValue(students.get(i).getFirstName());
            rowCell1.setCellStyle(cellStyle);

            Cell rowCell2 = row.createCell(2);
            rowCell2.setCellValue(students.get(i).getSecondName());
            rowCell2.setCellStyle(cellStyle);

            Cell rowCell3 = row.createCell(3);
            rowCell3.setCellValue(students.get(i).getLastName());
            rowCell3.setCellStyle(cellStyle);

            Cell rowCell4 = row.createCell(4);
            rowCell4.setCellValue(students.get(i).getDateBirth().toString());
            rowCell4.setCellStyle(cellStyle);

            Cell rowCell5 = row.createCell(5);

            rowCell5.setCellValue(students.get(i).getGender().toString());
            rowCell5.setCellStyle(cellStyle);

            Cell rowCell6 = row.createCell(6);
            rowCell6.setCellValue(students.get(i).getGroup().getName());
            rowCell6.setCellStyle(cellStyle);
        }
    }

    private void writeTeachersToFile() {
        String [] headers = { "№", "FirstName", "SecondName", "LastName", "DateBirth", "Gender" , "Category"};

        Sheet sheet = workbook.createSheet("Teachers");
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 5000);

        Row header = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerStyle);
        }

        List<Teacher> teachers =  teacherComponent.findAll();
        for (int i = 0; i < teachers.size(); i++) {
            Row row = sheet.createRow(i + 1);

            Cell rowCell0 = row.createCell(0);
            rowCell0.setCellValue(teachers.get(i).getId());
            rowCell0.setCellStyle(cellStyle);

            Cell rowCell1 = row.createCell(1);
            rowCell1.setCellValue(teachers.get(i).getFirstName());
            rowCell1.setCellStyle(cellStyle);

            Cell rowCell2 = row.createCell(2);
            rowCell2.setCellValue(teachers.get(i).getSecondName());
            rowCell2.setCellStyle(cellStyle);

            Cell rowCell3 = row.createCell(3);
            rowCell3.setCellValue(teachers.get(i).getLastName());
            rowCell3.setCellStyle(cellStyle);

            Cell rowCell4 = row.createCell(4);
            rowCell4.setCellValue(teachers.get(i).getDateBirth().toString());
            rowCell4.setCellStyle(cellStyle);

            Cell rowCell5 = row.createCell(5);

            rowCell5.setCellValue(teachers.get(i).getGender().toString());
            rowCell5.setCellStyle(cellStyle);

            Cell rowCell6 = row.createCell(6);
            rowCell6.setCellValue(teachers.get(i).getCategory());
            rowCell6.setCellStyle(cellStyle);
        }
    }

    private void close() throws IOException {
        currDir = new File("");
        outputStream = new FileOutputStream(currDir.getAbsolutePath() + filePath);
        workbook.write(outputStream);
        workbook.close();
    }

    public void create() throws IOException {
        writeGroupsToFile();
        writeSubjectsToFile();
        writeStudentsToFile();
        writeTeachersToFile();
        close();
    }
}
