# Merchant Integration Documentation(Backend JAVA Auth SDK)

----
> ## A. OTPLessAuth Dependency
Add Below dependency in your project's `pom.xml` file.

```xml

<dependency>
    <groupId>io.github.otpless-tech.auth</groupId>
    <artifactId>otpless-auth</artifactId>
    <version>1.0.8</version>
</dependency>
```

you can also get latest version of dependency
at https://central.sonatype.com/artifact/io.github.otpless-tech.auth/otpless-auth

--------------------

> ## B. OTPLessAuth class


The `OTPLessAuth` class provides methods to integrate OTPLess authentication into your Java backend application. This
documentation explains the usage of the class and its methods.

### Methods:

---

> ### 1. Verify Auth Token

This method help to resolve `token` which is issued by `OTPLess` which return user detail
from that token also this method verify that token is valid, token should not expired and
issued by only `otpless.com`

##### Method Signature:

```java
public static UserDetail verifyToken(String token, String clientId, String clientSecret)
```

#### Method Params:

Params       | Data type    | Mandatory | Constraints | Remarks
--------------|--------------|-----------|-------------|------------------------------------------------------------------------------------
token        | String       | true      |             | token which you get from `OTPLess`
clientId     | String       | true      |             | Your OTPLess `Client Id`
clientSecret | String       | true      |             | Your OTPLess `Client Secret`

#### Return

Return:
Object Name: UserDetail
-------

> ### 2. Decode IdToken

This method help to resolve `idToken(JWT token)` which is issued by `OTPLess` which return user detail
from that token also this method verify that token is valid, token should not expired and
issued by only `otpless.com`

##### Method Signature:

```java
public static UserDetail decodeIdToken(String idToken, String clientId, String clientSecret)
```

#### Method Params:

Params       | Data type    | Mandatory | Constraints | Remarks
--------------|--------------|-----------|-------------|------------------------------------------------------------------------------------
idToken      | String       | true      |             | idToken which is JWT token which you get from `OTPLess` by exchange code API
clientId     | String       | true      |             | Your OTPLess `Client Id`
clientSecret | String       | true      |             | Your OTPLess `Client Secret`

#### Return

Return:
Object Name: UserDetail
--------------------

> ### 3. Verify Code

This method help to resolve `code` which is return from `OTPLess` which will return user detail
from that code also this method verify that code is valid, code should not expired and
issued by only `otpless.com`

##### Method Signature:

```java
public static UserDetail verifyCode(String code, String clientId, String clientSecret)
```

#### Method Params:

Params       | Data type    | Mandatory | Constraints | Remarks
--------------|--------------|-----------|-------------|------------------------------------------------------------------------------------
code         | String       | true      |             | code  which you get from `OTPLess`
clientId     | String       | true      |             | Your OTPLess `Client Id`
clientSecret | String       | true      |             | Your OTPLess `Client Secret`

#### Return

Return:
Object Name: UserDetail

---

> ### 4. Generate Magic Link

This method initiates the authentication process by sending a "magic link"
to the user's WhatsApp/SMS or email, based on the provided contact information.
This link is used to verify the identity of the user. Upon the user's action
on this link, they are redirected to the specified URI with an authorization
code included in the redirection.

##### Method Signature:

```java
public static MagicLinkTokens generateMagicLink(String mobile, String email, String redirectURI, String clientId, String clientSecret, String channel)
```

#### Method Params:

Params       | Data type    | Mandatory | Constraints  | Remarks
--------------|--------------|-----------|--------------|-------------------------------------------------------------------------------------------
mobile       | String       | false     |              | An phone number on which you want to send a magic link.
email        | String       | false     |              | An email address on which you want to send a magic link.
redirectURI  | String       | false     |              | URL on which you want to redirect a user after successful authentication
clientId     | String       | true      |              | Your OTPLess `Client Id`
clientSecret | String       | true      |              | Your OTPLess `Client Secret`
channel      | String       | false     | WHATSAPP/SMS | Channel on which you want to send magic link like WHATSAPP/SMS. by default it is WHATSAPP

#### Return

Return:
Object Name: MagicLinkTokens

---

> ### 5. OTP

These methods enable you to send, resend and verify OTP

### 5A. Send OTP

##### Method Signature:


```java
    public static OTPResponse sendOTP(String sendTo, String orderId, String hash, String clientId, String clientSecret, Integer otpLength, String channel)
```

#### Method Params:

Params       | Data type    | Mandatory | Constraints         | Remarks
--------------|--------------|-----------|---------------------|------------------------------------------------------------------------------------
sendTO       | String       | true      |                     | An phone number on which you want to send a OTP.
orderId      | String       | true      |                     | An Merchant unique id for the request.
hash         | String       | false     |                     | An Hash will be used to auto read OTP.
clientId     | String       | true      |                     | Your OTPLess `Client Id`
clientSecret | String       | true      |                     | Your OTPLess `Client Secret`
otpLength    | Integer      | false     | 4 or 6 only allowed | Allowes you to send OTP in 4/6 digit. default will be 6 digit.
channel      | String       | false     | SMS/WHATSAPP/ALL    | Allowes you to send OTP on WhatsApp/SMS/Both. default will be SMS

#### Return

Return:
Object Name: OTPResponse

---

### 5B. Resend OTP

##### Method Signature:


```java
    public static OTPResponse resendOTP(String orderId, String clientId, String clientSecret)
```

#### Method Params:

Params       | Data type    | Mandatory | Constraints | Remarks
--------------|--------------|-----------|-------------|------------------------------------------------------------------------------------
orderId      | String       | true      |             | An Merchant unique id for the request.
clientId     | String       | true      |             | Your OTPLess `Client Id`
clientSecret | String       | true      |             | Your OTPLess `Client Secret`

#### Return

Return:
Object Name: OTPResponse

---

### 5C. Verify OTP

##### Method Signature:


```java
    public static OTPVerificationResponse verifyOTP(String sendTo, String orderId, String otp, String clientId, String clientSecret)
```

#### Method Params:

Params       | Data type    | Mandatory | Constraints | Remarks
--------------|--------------|-----------|-------------|------------------------------------------------------------------------------------
sendTo       | String       | true      |             | An phone number on which OTP has been sent.
orderId      | String       | true      |             | An Merchant unique id for the request.
otp          | String       | true      |             | OTP value.
clientId     | String       | true      |             | Your OTPLess `Client Id`
clientSecret | String       | true      |             | Your OTPLess `Client Secret`

#### Return

Return:
Object Name: OTPVerificationResponse

---


> ### UserDetail Object Fields:

`success` (boolean): This will be `true` in case of method successfully performed operation.<br>
`authTime` (Long, required): The time when authentication was completed.<br>
`phoneNumber` (String, required): The user's phone number.<br>
`countryCode` (String, required): The country code of user's phone number.<br>
`nationalPhoneNumber` (String, required): The user's phone number without country code.<br>
`email` (String, required): The user's email address.<br>
`name` (String, required): The user's full name.<br>

---

> ### MagicLinkTokens Object Fields:

`success` (boolean): This will be `true` in case of method successfully performed operation.<br>
`requestIds` (List, required): A list of object which contains type and
`OTPLess` Request Id to track the request.
it's a List of Object as you can verify multiple channel like WhatsApp/Email .<br>

### requestIds Object Fields:

`type` (String): The channel type like MOBILE/EMAIL.<br>
`requestIds` (String): `OTPLess` Request Id to track the request .<br>

---

> ### OTPResponse Object Fields:

`success` (boolean): This will be `true` in case of method successfully performed operation.<br>
`errorMessage` (String): If success `false` then it will be return reason of error.<br>
`orderId` (String, required): OrderId which you passed.<br>
`refId` (String, required): OTPLess reference id.<br>

---

> ### OTPVerificationResponse Object Fields:

`success` (boolean): This will be `true` in case of method successfully performed operation.<br>
`errorMessage` (String): If success `false` then it will be return reason of error.<br>
`isOTPVerified` (Boolean, required): Indicate for valid OTP.<br>
`reason` (String): This will be return in case of `isOTPVerified` is `false` .<br>

---

### Error case:

`success` (boolean): This will be `false`. The method is failed to perform.<br>
`errorMessage` (String): The message contains error information.<br>

### Example of usage

```java
    try{
        UserDetail userDetail = OTPLessAuth.decodeIdToken(
                "your_id_token_here",
                "your_client_id_here",
                "your_client_secret_here"
        );
        
        // Access user details
        System.out.println("User Name: "+userDetail.getName());
        System.out.println("User Email: "+userDetail.getEmail());
        // ... (Access other user details as needed)
        
    } catch(Exception e){
        // Handle exceptions
    }
```

This method allows you to decode and verify OTPLess tokens and retrieve user information for integration into your
backend Java application.

--------

> ## B. OTPAuth class


The `OTPAuth` class provides methods to integrate OTP authentication into your Java backend application. This
documentation explains the usage of the class and its methods.

##### Class Constructors:

```java
    public OTPAuth(String clientId, String clientSecret)
```

### Methods:

---

### 1. Send OTP

##### Method Signature:

```java
    public OTPResponse sendOTP(String orderId, String phoneNumber, String email, String hash, Integer expiredInSec, Integer otpLength, String channel)
```

#### Method Params:

Params       | Data type | Mandatory | Constraints                         | Remarks
--------------|-----------|-----------|-------------------------------------|--------------------------------------------------------------------------------------------------------------------------
orderId      | String    | false     |                                     | An Merchant unique id for the request.
phoneNumber  | String    | false     |                                     | An Phone number on which you want send OTP.
email        | String    | false     |                                     | An Email address on which you want send OTP.
hash         | String    | false     | alphanumeric                        | An Hash will be used to auto read OTP.
expiredInSec | Integer   | false     | sec. should be in 30 to 600 allowed | Allows you manage OTP expiry . default will be 60 for phone number and 120 for email address.
otpLength    | Integer   | false     | 4 or 6 only allowed                 | Allows you to send OTP in 4/6 digit. default will be 6 digit.
channel      | String    | false     | SMS/WHATSAPP/EMAIL                  | Allows you to send OTP on WhatsApp/SMS/Email. default will be SMS if phone number in request and Email for email address

#### Return

Return:
Object Name: OTPResponse

---

### 2. Resend OTP

##### Method Signature:

```java
    public OTPResponse resendOTP(String orderId)
```

#### Method Params:

Params  | Data type | Mandatory | Constraints | Remarks
---------|-----------|-----------|-------------|----------------------------------------------------------------------------------------------------
orderId | String    | true      |             | An order id which you passed in request if not passed then our order id which you get in response.

#### Return

Return:
Object Name: OTPResponse

---

### 3. Verify OTP

##### Method Signature:

```java
    public OTPVerificationResponse verifyOTP(String orderId, String otp, String phoneNumber, String email)
```

#### Method Params:

Params      | Data type | Mandatory | Constraints | Remarks
-------------|-----------|-----------|-------------|----------------------------------------------------------------------------------------------------
orderId     | String    | true      |             | An order id which you passed in request if not passed then our order id which you get in response.
otp         | String    | true      | numeric     | An OTP which received on phone number/otp.
phoneNumber | String    | false     |             | An Phone number on which you sent OTP.
email       | String    | false     |             | An Email address on which you sent OTP.

#### Return

Return:
Object Name: OTPVerificationResponse

---

> ### OTPResponse Object Fields:

`success` (boolean): This will be `true` in case of method successfully performed operation.<br>
`errorMessage` (String): If success `false` then it will be return reason of error.<br>
`orderId` (String, required): OrderId which you passed.<br>
`refId` (String, required): OTPLess reference id.<br>

---

> ### OTPVerificationResponse Object Fields:

`success` (boolean): This will be `true` in case of method successfully performed operation.<br>
`errorMessage` (String): If success `false` then it will be return reason of error.<br>
`isOTPVerified` (Boolean, required): Indicate for valid OTP.<br>
`reason` (String): This will be return in case of `isOTPVerified` is `false` .<br>

---

### Error case:

`success` (boolean): This will be `false`. The method is failed to perform.<br>
`errorMessage` (String): The message contains error information.<br>

### Example of usage

```java
    import com.otpless.authsdk.OTPAuth;
    import com.otpless.authsdk.OTPResponse;
    import com.otpless.authsdk.OTPVerificationResponse;
    
    try {
        OTPAuth otpAuth = new OTPAuth("YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET");
        
        // send OTP
        OTPResponse otpResponse = otpAuth.sendOTP("DT0111", "917069914791", "dhaval.limbani@otpless.com"
                , "ABC1234", 60, 4, "SMS,EMAIL");
        if (otpResponse.success) {
            System.out.println("OTP sent. orderId=> " + otpResponse.orderId);
        } else {
            System.out.println("OTP send to failed due to " + otpResponse.errorMessage);
        }
        
        // resend OTP
        otpResponse = otpAuth.resendOTP("DT0111");
        if (otpResponse.success) {
            System.out.println("OTP resent. orderId=> " + otpResponse.orderId);
        } else {
            System.out.println("OTP not resend due to " + otpResponse.errorMessage);
        }
        
        // Verify OTP
        OTPVerificationResponse otpVerificationResponse = otpAuth.verifyOTP("DT0111", "4321", "917069914791"
                , "dhaval.limbani@otpless.com");
        if (otpVerificationResponse.success) {
            System.out.println("OTP verify API SUCCESS");
            if (otpVerificationResponse.isOTPVerified) {
                System.out.println("OTP verified");
            } else {
                System.out.println("OTP verification failed. due to " + otpVerificationResponse.reason);
            }
        } else {
            System.out.println("OTP verification failed in API. due to " + otpVerificationResponse.errorMessage);
        }
    } catch (Exception e) {
        // Handle exceptions
    }
```