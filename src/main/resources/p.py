from bcrypt import checkpw

hashed_password = b"$2a$10$bHd0C819L1uc1Al5fAPqoeAIJM9qe4m1mTmcNlrsUvUEhRKzvu.bu"
plain_password = b"admin123"

print(checkpw(plain_password, hashed_password))  # Должно вывести True
