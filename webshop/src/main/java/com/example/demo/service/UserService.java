package com.example.demo.service;

import com.example.demo.model.Photo;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public void addUser(User user){
        String password = user.getPassword();
        String f = new BCryptPasswordEncoder().encode(password);

        user.setPassword(f);
        userRepository.save(user);
    }
    public Product fileUpload(Product product, MultipartFile[] image) throws IOException {
        //file upload
        File dir = new File("d:\\java");
        if (!dir.exists()) {
            dir.mkdir();
        }
        List<Photo> photos=new ArrayList<>();
        Photo photo;
        //create the file on server
        for (MultipartFile multipartFile : image) {


            String productPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + "\\" + productPic);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(multipartFile.getBytes());
            stream.close();
            photo=new Photo();
            photo.setPath(productPic);
            photo.setProduct(product);
            photos.add(photo);
        }
        product.setPhotos(photos);
        return product;


    }
}
