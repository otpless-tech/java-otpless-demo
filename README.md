# Demo App Documentation

This document provides an overview and usage instructions for the `APIAuthController` class in the OTPLess Java SDK.

## Overview

The `APIAuthController` class provides several endpoints for authentication services, including initiating OTPs (One-Time Passwords), magic links, OTP + magic links, verifying OTPs, initiating OAuth (WhatsApp, Google, Apple, and many more), and checking authentication status.

## A. OTPLessAuth Dependency

Add the following dependency to your project's `pom.xml` file:

```xml
<dependency>
    <groupId>io.github.otpless-tech.auth</groupId>
    <artifactId>otpless-auth</artifactId>
    <version>1.1.0</version>
</dependency>
```

You can also get the latest version of the dependency at [Sonatype Central](https://central.sonatype.com/artifact/io.github.otpless-tech.auth/otpless-auth).

---

## B. API Endpoints of Demo Project

Just replace `YOUR_OTPLESS_CLIENT_ID` and `YOUR_OTPLESS_CLIENT_SECRET` in `application.properties` with your credentials and run the project. The project runs on port 8080 by default.

### 1. Send OTP to Phone Number

**Endpoint:**

```
GET /auth/initiate/otp/phoneNumber
```

**Query Parameters:**

- `phoneNumber` (required): The phone number to which the OTP will be sent.
- `otpLength` (optional): The length of the OTP.

**Example Request:**

```
GET /auth/initiate/otp/phoneNumber?phoneNumber=9170XXXXXXXX&otpLength=4
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/initiate/otp/phoneNumber?phoneNumber=9170XXXXXXXX&otpLength=4"
```

### 2. Send OTP to Email

**Endpoint:**

```
GET /auth/initiate/otp/email
```

**Query Parameters:**

- `email` (required): The email address to which the OTP will be sent.
- `otpLength` (optional): The length of the OTP.

**Example Request:**

```
GET /auth/initiate/otp/email?email=user@example.com&otpLength=6
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/initiate/otp/email?email=user@example.com&otpLength=6"
```

### 3. Verify OTP

**Endpoint:**

```
GET /auth/verify/otp
```

**Query Parameters:**

- `otp` (required): The OTP to verify.
- `requestId` (required): The request ID associated with the OTP. You get this from the initiate API.

**Example Request:**

```
GET /auth/verify/otp?otp=123456&requestId=abcdef
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/verify/otp?otp=123456&requestId=abcdef"
```

### 4. Send Magic Link to Phone Number

**Endpoint:**

```
GET /auth/initiate/magiclink/phoneNumber
```

**Query Parameters:**

- `phoneNumber` (required): The phone number to which the magic link will be sent.
- `redirectURI` (required): The URI to redirect to after the magic link is used.

**Example Request:**

```
GET /auth/initiate/magiclink/phoneNumber?phoneNumber=917069914791&redirectURI=https://example.com
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/initiate/magiclink/phoneNumber?phoneNumber=917069914791&redirectURI=https://example.com"
```

### 5. Send Magic Link to Email

**Endpoint:**

```
GET /auth/initiate/magiclink/email
```

**Query Parameters:**

- `email` (required): The email address to which the magic link will be sent.
- `redirectURI` (required): The URI to redirect to after the magic link is used.

**Example Request:**

```
GET /auth/initiate/magiclink/email?email=user@example.com&redirectURI=https://example.com
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/initiate/magiclink/email?email=user@example.com&redirectURI=https://example.com"
```

### 6. Send Magic Link and OTP to Phone Number

**Endpoint:**

```
GET /auth/initiate/otplink/phoneNumber
```

**Query Parameters:**

- `phoneNumber` (required): The phone number to which the magic link and OTP will be sent.
- `redirectURI` (required): The URI to redirect to after the magic link is used.

**Example Request:**

```
GET /auth/initiate/otplink/phoneNumber?phoneNumber=917069914791&redirectURI=https://example.com
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/initiate/otplink/phoneNumber?phoneNumber=917069914791&redirectURI=https://example.com"
```

### 7. Send Magic Link and OTP to Email

**Endpoint:**

```
GET /auth/initiate/otplink/email
```

**Query Parameters:**

- `email` (required): The email address to which the magic link and OTP will be sent.
- `redirectURI` (required): The URI to redirect to after the magic link is used.

**Example Request:**

```
GET /auth/initiate/otplink/email?email=user@example.com&redirectURI=https://example.com
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/initiate/otplink/email?email=user@example.com&redirectURI=https://example.com"
```

### 8. Initiate OAuth

**Endpoint:**

```
GET /auth/initiate/oauth
```

**Query Parameters:**

- `channel` (required): The OAuth channel to initiate.
- `redirectURI` (required): The URI to redirect to after the OAuth flow is completed.

**Example Request:**

```
GET /auth/initiate/oauth?channel=google&redirectURI=https://example.com
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/initiate/oauth?channel=google&redirectURI=https://example.com"
```

### 9. Verify Code

**Endpoint:**

```
GET /auth/verify/code
```

**Query Parameters:**

- `code` (required): The code to verify. You get this on your `redirectURI` with param `code`.

**Example Request:**

```
GET /auth/verify/code?code=abcdef
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/verify/code?code=abcdef"
```

### 10. Get Authentication Status

**Endpoint:**

```
GET /auth/status
```

**Query Parameters:**

- `requestId` (required): The request ID to check the status of.

**Example Request:**

```
GET /auth/status?requestId=abcdef
```

**Example cURL:**

```sh
curl -X GET "http://localhost:8080/auth/status?requestId=abcdef"
```

---

## Running the Application

1. Replace `YOUR_OTPLESS_CLIENT_ID` and `YOUR_OTPLESS_CLIENT_SECRET` in `application.properties` with your actual credentials.
2. Run the Spring Boot application. The application will start on port 8080 by default.

```sh
mvn spring-boot:run
```

Or, you can run the application using your IDE's run configuration.

This setup provides a comprehensive guide to using the `APIAuthController` class in your OTPLess Java SDK, along with instructions for running the application and example requests using cURL.
