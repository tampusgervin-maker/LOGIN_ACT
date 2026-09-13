# Implementation Plan - Login Form with Activity Validation

This plan implements a login form using traditional Android Views (XML) as requested. It follows the approach of using `registerForActivityResult` to carry out validation in a second activity and return the result.

## Proposed Changes

### Configuration

#### [MODIFY] [libs.versions.toml](file:///D:/ACT2_AS/gradle/libs.versions.toml)
- Add `appcompat` and `material` dependencies to support traditional UI components and themes.

#### [MODIFY] [app/build.gradle.kts](file:///D:/ACT2_AS/app/build.gradle.kts)
- Enable `viewBinding` to easily interact with XML layouts.
- Add `appcompat` and `material` to the dependencies.
- (Keep Compose enabled but we will use `AppCompatActivity` for the main task).

### Layouts

#### [NEW] [activity_main.xml](file:///D:/ACT2_AS/app/src/main/res/layout/activity_main.xml)
- Create a layout with:
    - `TextView` for the "Please enter..." instruction.
    - `EditText` for username (hint: "Enter your username:").
    - `EditText` for password (hint: "Enter your password:", inputType: `textPassword`).
    - `Button` for LOGIN.
    - A hidden `TextView` for the Welcome message.
    - An error `TextView` to display validation errors.

#### [NEW] [activity_validation.xml](file:///D:/ACT2_AS/app/src/main/res/layout/activity_validation.xml)
- A simple layout for the validation activity (though it may mostly run logic and return).

### Logic

#### [MODIFY] [MainActivity.kt](file:///D:/ACT2_AS/app/src/main/java/com/example/login_act/MainActivity.kt)
- Change base class to `AppCompatActivity`.
- Implement View Binding.
- Set up `registerForActivityResult` for the `ValidationActivity`.
- In `onCreate`, set up the button click listener:
    - Validate that fields are not empty.
    - Start `ValidationActivity` with the entered credentials.
- Handle the result:
    - If successful: Hide the login form views and show the welcome message.
    - If unsuccessful: Show an error message and keep the form visible.

#### [NEW] [ValidationActivity.kt](file:///D:/ACT2_AS/app/src/main/java/com/example/login_act/ValidationActivity.kt)
- Receives username and password via `Intent`.
- Checks against hardcoded values (e.g., `admin` / `password123`).
- If match: Return `RESULT_OK` with the username.
- If no match: Return `RESULT_CANCELED` with an error message.
- Finishes immediately (since the UI for "Welcome" is requested to be in the form of "hiding the form" in the instructions, or I can show it here if preferred. I'll stick to returning the result to `MainActivity` to "hide the form" as per the requirement).

### Manifest

#### [MODIFY] [AndroidManifest.xml](file:///D:/ACT2_AS/app/src/main/AndroidManifest.xml)
- Register `ValidationActivity`.
- Ensure a proper theme is used (e.g., `Theme.Material3.DayNight.NoActionBar` or similar, but the image shows an ActionBar). I'll use `Theme.AppCompat.Light.DarkActionBar`.

## Verification Plan

### Automated Tests
- None requested, but manual verification will be performed.

### Manual Verification
1.  Launch the app.
2.  Verify the UI matches the screenshot.
3.  Click "LOGIN" without entering anything -> Verify error message.
4.  Enter incorrect credentials -> Verify error message.
5.  Enter correct credentials -> Verify "Welcome [username]" message appears and the form disappears.
