package de.alexanderwodarz.code.jamf.now;

import com.sun.jersey.api.client.ClientResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result {

    private final ClientResponse clientResponse;
    private final String response;
    private final int status;

}
