package com.shopme.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.shopme.admin.AbstractExporter;
import com.shopme.common.entity.Category;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class CategoryCsvExporter extends AbstractExporter {
    
	public void export(List<Category> listCategory, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "test/csv", ".csv", "categories");
		
			ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Category ID", "Category Name"};
		String[] fieldMapping = {"id", "name"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(Category category: listCategory){
            category.setName(category.getName().replace("--", "  "));
            csvWriter.write(category, fieldMapping);
        }
		
		csvWriter.close();
	}
}
