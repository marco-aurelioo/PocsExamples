package com.jsonb.postgres.PostgresJsonb.job

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import com.amazonaws.util.IOUtils
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileOutputStream
import java.net.URL


@Component
class JobListenerS3 {

    var countCreated = 0
    var countDeleted = 0

    @Scheduled(fixedDelay = 5000)
    fun checkS3(){
        val client = setup()
        val myfile = File("temp.txt")
        myfile.writeText("one cool text $countCreated")
        countCreated ++

        client.putObject(PutObjectRequest(
            "/bucket/file/","teste$countCreated.txt", myfile
        ))
        client.download(PresignedUrlDownloadRequest(
            URL("http://localhost:4566/bucket/file/teste.txt")
        ), myfile)
        print(myfile.exists())
        myfile.readLines().forEach(){
            print("$it\n")
        }
    }

    @Scheduled(fixedDelay = 5000)
    fun checkS3Delete(){
        val client = setup()
        val obj = client.getObject(
            GetObjectRequest("/bucket/file/","teste$countDeleted.txt")
        )
        val file = File("tmp$countDeleted.txt")
        IOUtils.copy(obj.getObjectContent(),  FileOutputStream( file));
        file.readLines().forEach(){
            print("$it\n")
        }
        client.deleteObject(
            DeleteObjectRequest(
                "/bucket/file/","teste$countDeleted.txt"
            )
        )
    }

    fun setup(): AmazonS3 {

        val credentials: AWSCredentials = BasicAWSCredentials(
            "fake",
            "fake"
        )
        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_1)
            .build()
    }

}