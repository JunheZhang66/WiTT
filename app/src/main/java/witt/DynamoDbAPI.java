package witt;

import com.amazonaws.amplify.generated.graphql.CreateTodoMutation;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;

import type.CreateTodoInput;

public class DynamoDbAPI {

    AWSAppSyncClient client;

    public DynamoDbAPI(AWSAppSyncClient client) {
        this.client = client;
    }

    public void runMutation(){
        CreateTodoInput createTodoInput = CreateTodoInput.builder()
                .text("hello")
                .translation("world")
                .build();
        client.mutate(CreateTodoMutation.builder()
        .input(createTodoInput).build());
    }
}
