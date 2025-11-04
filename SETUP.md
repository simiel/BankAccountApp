# Setup Instructions for Android Studio

## Step 1: Open the Project

1. **Launch Android Studio** (Hedgehog or later recommended)
2. Click **"Open"** or **File → Open**
3. Navigate to and select the `BankAccountApp` folder
4. Click **"OK"**

## Step 2: Wait for Gradle Sync

Android Studio will automatically:
- Detect the project structure
- Download Gradle wrapper (if needed)
- Sync dependencies
- Index the project

**This may take a few minutes on first open.**

## Step 3: Configure SDK (if needed)

If prompted:
1. Go to **Tools → SDK Manager**
2. Ensure you have:
   - Android SDK Platform 34
   - Android SDK Build-Tools
   - Android Emulator (if using emulator)

## Step 4: View Previews

### Option A: Compose Preview (Recommended for Quick UI Testing)

1. Open `app/src/main/java/com/bankaccountapp/ui/screens/BankAccountScreen.kt`
2. Look for the **Preview** panel on the right side of the editor
3. If you don't see it:
   - Click the **split screen icon** (or press `Alt + Shift + Enter` on Windows/Linux, `Option + Shift + Enter` on Mac)
   - Or go to **View → Tool Windows → Preview**
4. You'll see previews for:
   - Account Creation Screen
   - Account Operations (Debit)
   - Account Operations (Credit)
   - Full Screen

5. **To refresh previews**: Click the refresh icon or press `Ctrl + Shift + R` (Windows/Linux) or `Cmd + Shift + R` (Mac)

### Option B: Run on Emulator/Device

1. **Create/Select an Emulator**:
   - Tools → Device Manager
   - Create a new Virtual Device (recommended: Pixel 5 or newer)
   - Select API level 24 or higher

2. **Run the App**:
   - Click the green **Run** button (▶️)
   - Or press `Shift + F10` (Windows/Linux) or `Ctrl + R` (Mac)
   - Select your emulator or device

## Troubleshooting

### "Gradle sync failed"
- Check your internet connection
- Try: **File → Invalidate Caches → Invalidate and Restart**
- Ensure you have Java 17 installed: **File → Project Structure → SDK Location**

### "Preview not showing"
- Ensure you're using Android Studio Hedgehog or later
- Try: **Build → Rebuild Project**
- Check that Compose dependencies are synced correctly

### "SDK not found"
- Go to **Tools → SDK Manager**
- Install Android SDK Platform 34
- Set SDK location in **File → Project Structure → SDK Location**

## Quick Tips

- **Build Variants**: Use "debug" for development
- **Preview Mode**: Best for UI development - no emulator needed
- **Live Edit**: Some changes reflect instantly in preview
- **Multiple Previews**: You can see all previews at once in the preview panel

## Project Structure

```
BankAccountApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/bankaccountapp/
│   │   │   ├── MainActivity.kt          # App entry point
│   │   │   ├── model/                    # Data models
│   │   │   ├── viewmodel/                # State management
│   │   │   └── ui/
│   │   │       ├── screens/              # UI screens (with previews!)
│   │   │       └── theme/                # Theme configuration
```

## Next Steps

Once set up, you can:
- ✅ View UI previews instantly
- ✅ Run the app on emulator/device
- ✅ Make changes and see them in real-time
- ✅ Test all account types and transactions

Happy coding! 🚀

