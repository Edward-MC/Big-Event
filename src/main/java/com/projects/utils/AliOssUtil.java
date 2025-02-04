package com.projects.utils;

import com.aliyun.oss.*;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@Component
public class AliOssUtil {

    private static String ENDPOINT;
    private static String BUCKET_NAME;
    private static String ACCESS_KEY_ID;
    private static String SECRET_ACCESS_KEY;

    private static HashMap<String, String> credentialMap = new HashMap<>();

    // Upload File, return file public url
    public static String uploadFile(String objectName, InputStream inputStream) {
        ENDPOINT = getCredentials("ENDPOINT");
        BUCKET_NAME = getCredentials("BUCKET_NAME");
        ACCESS_KEY_ID = getCredentials("ACCESS_KEY_ID");
        SECRET_ACCESS_KEY = getCredentials("SECRET_ACCESS_KEY");
        System.out.println("ENDPOINT:  " + ENDPOINT);
        System.out.println("BUCKET_NAME:  " + BUCKET_NAME);
        System.out.println("ACCESS_KEY_ID:  " + ACCESS_KEY_ID);
        System.out.println("SECRET_ACCESS_KEY:  " + SECRET_ACCESS_KEY);

        // Create OSSClient Instance
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT,ACCESS_KEY_ID,SECRET_ACCESS_KEY);
        // File Visiting URL
        String url = "";
        try {
            // Create storage space
            ossClient.createBucket(BUCKET_NAME);
            ossClient.putObject(BUCKET_NAME, objectName, inputStream);
            url = "https://"+BUCKET_NAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/"+objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }

    private static String getCredentials(String key) {
        if (credentialMap.get(key) != null) {
            return credentialMap.get(key);
        }
        String filePath = System.getProperty("user.dir") + "/src/main/resources/config/credentials";
        try {
            String content = Files.readString(Paths.get(filePath));
            String[] lines = content.split("\n");
            for (String line: lines) {
                String[] items = line.strip().split("=");
                credentialMap.put(items[0], items[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credentialMap.get(key);
    }
}
