package com.laf.dto;

import lombok.Data;

@Data
public class CreateLostOrFoundItemDTO {
    private String itemName;
    private String description;
    private String location;
    private String time;
    private String imageUrl;
    private String itemType;
    private Integer lostOrFound;
}