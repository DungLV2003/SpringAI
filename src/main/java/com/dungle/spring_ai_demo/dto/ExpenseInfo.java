package com.dungle.spring_ai_demo.dto;


//Record chỉ để chứa dữ liệu, không cân các method getter setter
public record ExpenseInfo(String category, String itemName, Double amount) {

}
