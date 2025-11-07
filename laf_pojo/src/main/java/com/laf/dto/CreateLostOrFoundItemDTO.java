package com.laf.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CreateLostOrFoundItemDTO implements Serializable {
    private String itemName;
    private String description;
    private String location;
    private String time;
    private String imageUrl;
    private String itemType;
    private Integer lostOrFound;
}