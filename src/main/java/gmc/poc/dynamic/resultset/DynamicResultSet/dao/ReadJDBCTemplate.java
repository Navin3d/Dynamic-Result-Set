package gmc.poc.dynamic.resultset.DynamicResultSet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Repository
public class ReadJDBCTemplate {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<JsonObject> execute(String sql, List<String> fields) {
		List<JsonObject> returnedValue = jdbcTemplate.query(sql, new ResultSetExtractor<List<JsonObject>>() {
			@Override
			public List<JsonObject> extractData(ResultSet rs) throws SQLException {
				List<JsonObject> returnValue = new ArrayList<>();
				
				while(rs.next()) {
					JsonObject object = new JsonObject();
					for(String field: fields) {
						JsonElement jsonElement = JsonParser.parseString(rs.getString(field));
						object.add(field, jsonElement);
					}
					returnValue.add(object);
				}
				
				return returnValue;
			}
		});
		return returnedValue;
	}

}
