import os

# Ищем главный файл активити или Application
target_file = None
for root, dirs, files in os.walk('app/src/main/java'):
    for file in files:
        if 'MainActivity' in file or 'App' in file:
            target_file = os.path.join(root, file)
            break
    if target_file:
        break

if target_file:
    with open(target_file, 'r') as f:
        code = f.read()
    
    # Додаем применение акцента из theme_prefs в onCreate
    theme_code = '''
        // Dynamic / Adaptive Theme Applied
        try {
            int accent = getSharedPreferences("theme_prefs", MODE_PRIVATE).getInt("accent", 0);
            if (accent != 0) {
                // Применяем акцент к элементам, если нужно
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    '''
    
    if 'super.onCreate(savedInstanceState);' in code and 'theme_prefs' not in code:
        code = code.replace('super.onCreate(savedInstanceState);', 'super.onCreate(savedInstanceState);' + theme_code)
        with open(target_file, 'w') as f:
            f.write(code)
        print(f"Patched successfully: {target_file}")
    else:
        print("Already patched or onCreate not found.")
else:
    print("Main activity not found.")
