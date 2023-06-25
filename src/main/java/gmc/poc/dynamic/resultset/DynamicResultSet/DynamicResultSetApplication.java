package gmc.poc.dynamic.resultset.DynamicResultSet;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.JsonObject;

import gmc.poc.dynamic.resultset.DynamicResultSet.dao.ReadJDBCTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DynamicResultSetApplication implements CommandLineRunner {
	
	@Value("${table.fileds}")
	private String fieldsString;
	
	@Autowired
	private ReadJDBCTemplate readJDBCTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DynamicResultSetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] fieldArray = fieldsString.split(", ");
		List<String> fields = Arrays.asList(fieldArray);
		String sql = "SELECT * FROM users;";
		List<JsonObject> data = readJDBCTemplate.execute(sql, fields);
		data.forEach(item -> {
			log.info("Datas from Data Base - {}", item.toString());
		});
	}

}
