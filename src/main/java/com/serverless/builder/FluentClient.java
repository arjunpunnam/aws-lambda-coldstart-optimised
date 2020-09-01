package com.serverless.builder;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.SsmClientBuilder;

import java.util.Objects;

public final class FluentClient {

    private SsmClient ssmClient;
    private SqsClient sqsClient;

    public SsmClient getSsmClient() {
        return ssmClient;
    }

    public SqsClient getSqsClient() {
        return sqsClient;
    }

    public static Builder builder() {
        return new Builder();
    }


    public FluentClient(Builder builder)
    {
        this.ssmClient = builder.ssmClient;
        this.sqsClient = builder.sqsClient;
    }

    public static class Builder{

        private SsmClient ssmClient;
        private SqsClient sqsClient;

        public Builder withSSMClient(){
            this.ssmClient = SsmClient.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                    .build();
            return this;
        }

        public Builder withSQSClient(){
            this.sqsClient = SqsClient.builder()
                     .region(Region.US_EAST_1)
                     .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                     .build();
            return this;
        }

        public FluentClient build(){
            return new FluentClient(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Builder)) return false;
            Builder builder = (Builder) o;
            return ssmClient.equals(builder.ssmClient);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ssmClient);
        }
    }
}
