package com.adozgen.rabbitmqexample.service;

import lombok.Data;

import java.io.Serializable;

@Data
public class PdfData  implements Serializable {
    private String filePath;
    private String content;
}
