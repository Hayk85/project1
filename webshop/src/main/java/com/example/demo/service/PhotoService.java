package com.example.demo.service;

import com.example.demo.model.Photo;
import com.example.demo.model.Product;
import com.example.demo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getPhotoByProduct(Product product){
       return photoRepository.findByProduct(product);
    }
    public List<Photo> getAll(){
        return  photoRepository.findAll();
    }
    public void uploadPhoto(Product product, MultipartFile image) throws IOException {
        //file upload
        File dir = new File("d:\\java");
        if (!dir.exists()) {
            dir.mkdir();
        }
        //create the file on server
        String productPic = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File serverFile = new File(dir.getAbsolutePath() + "\\" + productPic);
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(serverFile));
        stream.read(image.getBytes());
        stream.close();
        Photo photo=new Photo();
        photo.setPath(productPic);
        photo.setProduct(product);
        photoRepository.saveAndFlush(photo);


    }
    public MultipartFile downloadPhoto(String name){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(name));
        } catch (IOException e) {
        }
        return (MultipartFile) img;

    }
    public List<Photo> getAllForShow(){
        List<Photo> photos=new LinkedList<>();
        return photos=photoRepository.findAll();
    }
    public List<Photo> getForShowByProduct(Product product){
        List<Photo> photos=new LinkedList<>();
         return photos=photoRepository.findByProduct(product);
    }
}
