package com.gfa.greenbay.services;

import com.gfa.greenbay.models.DTO.ErrorMessageDTO;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

  public String buildMissingFieldErrorMessage(List<String> list) {
    String result = "";
    String missingFields;
    for (String word : list) {
      result += word + ", ";
    }
    missingFields = result.substring(0, 1).toUpperCase() + result.substring(1, result.length() - 2).concat(" ") + "is required.";
    return missingFields;
  }

  public List<String> checkClassNullFields(Object object) {
    List<String> missingFields = new ArrayList<>();
    for (Field field : object.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      try {
        if (field.get(object) == null || field.get(object).equals("")) {
          field.setAccessible(true);
          missingFields.add(field.getName());
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return missingFields;
  }

  public ErrorMessageDTO defaultExceptionResponse(Exception exception) {
    ErrorMessageDTO error = new ErrorMessageDTO();
    error.setStatus("error");
    error.setMessage(exception.getMessage());
    return error;
  }
}
