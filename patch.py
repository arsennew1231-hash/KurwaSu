with open('app/src/main/java/df/root/SplashActivity.java', 'r') as f:
    code = f.read()

patch = '''
        View text = findViewById(R.id.splashText);
        if (text != null && text instanceof android.widget.TextView) {
            int accent = getSharedPreferences("theme_prefs", MODE_PRIVATE).getInt("accent", 0xFF6200EE);
            ((android.widget.TextView) text).setTextColor(accent);
        }
'''

if 'd.splashText);' in code:
    code = code.replace('d.splashText);', 'd.splashText);' + patch)
    with open('app/src/main/java/df/root/SplashActivity.java', 'w') as f:
        f.write(code)
    print("Successfully patched SplashActivity.java!")
else:
    print("Target string not found in SplashActivity.java")
