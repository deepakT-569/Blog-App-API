package com.deepak.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	String uploadImage(String path, MultipartFile file) throws IOException;
	
	InputStream getResource(String path, String fileName) throws FileNotFoundException;

}
