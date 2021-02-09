package readCSV;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompanyService implements ICompanyService {

    @Override
    public List<Company> read(String filename) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            List<Company> companies = new ArrayList<>();

            String line;
            while((line = reader.readLine()) != null){
                Company company = parse(line);
                companies.add(company);
            }

            return companies;

        } catch (FileNotFoundException e) {
            System.err.println("File: " + filename + " not found.");
        } catch (IOException e) {
            System.err.println("File: " + filename + " reading error.");
        }

        return new ArrayList<>();
    }

    private Company parse(String line){
        String[] fields = line.split(",");
        if (fields.length != 5) return null;
        Company company = new Company();
        company.setId(Integer.parseInt(fields[0]));
        company.setDate(LocalDate.parse(fields[1]));
        company.setName(fields[2]);
        company.setQuantity(Integer.parseInt(fields[3]));
        company.setPrice(new BigDecimal(fields[4]));

        return company;
    }
}
