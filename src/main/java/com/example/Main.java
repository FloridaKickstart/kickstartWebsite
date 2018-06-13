/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/index")
  String index() {
    return "index";
  }
  @RequestMapping("/aboutus")
  String aboutus() {
      return "aboutus";
  }
  @RequestMapping("/outsideresources")
  String outsideresources() {
      return "outsideresources";
  }
  @RequestMapping("/calendar")
  String calendar() {
      return "calendar";
  }
  @RequestMapping("/unit1video")
  String unit1video() {
      return "unit1video";
  }
  @RequestMapping("/unit1nearpod")
  String unit1nearpod() {
      return "unit1nearpod";
  }
  @RequestMapping("/unit1activity")
  String unit1activity() {
      return "unit1activity";
  }
    @RequestMapping("/unit1assessment")
    String unit1assessment() {
        return "unit1assessment";
    }
    @RequestMapping("/unit1feedback")
    String unit1feedback() {
        return "unit1feedback";
    }
    @RequestMapping("/unit1flipgrid")
    String unit1flipgrid() {
        return "unit1flipgrid";
    }
  @RequestMapping("/unit2video")
  String unit2video() {
      return "unit2video";
  }
  @RequestMapping("/unit2nearpod")
  String unit2nearpod() {
      return "unit2nearpod";
  }
    @RequestMapping("/unit2activity")
    String unit2activity() {
        return "unit2activity";
    }
    @RequestMapping("/unit2assessment")
    String unit2assessment() {
        return "unit2assessment";
    }
    @RequestMapping("/unit2feedback")
    String unit2feedback() {
        return "unit2feedback";
    }
    @RequestMapping("/unit2flipgrid")
    String unit2flipgrid() {
        return "unit2flipgrid";
    }
    @RequestMapping("/unit3video")
    String unit3video() {
        return "unit3video";
    }
    @RequestMapping("/unit3nearpod")
    String unit3nearpod() {
        return "unit3nearpod";
    }
    @RequestMapping("/unit3activity")
    String unit3activity() {
        return "unit3activity";
    }
    @RequestMapping("/unit3assessment")
    String unit3assessment() {
        return "unit3assessment";
    }
    @RequestMapping("/unit3feedback")
    String unit3feedback() {
        return "unit3feedback";
    }
    @RequestMapping("/unit3flipgrid")
    String unit3flipgrid() {
        return "unit3flipgrid";
    }
  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

}
