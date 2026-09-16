# 🪟 STEP 2: Database with H2 (Windows-Friendly!)

## 🎯 What We're Building

Same as regular Step 2, but with **H2 database** instead of PostgreSQL:
- ✅ In-memory database (runs inside your app!)
- ✅ User entity (auto-creates table!)
- ✅ Spring Data JPA (auto-generates SQL!)
- ✅ Full CRUD API
- ✅ **BONUS: Built-in web console to view your data!**

**Best part:** ZERO installation needed! 🎉

---

## ✨ Why H2?

**Perfect for Windows users who don't want PostgreSQL hassles!**

✅ No installation  
✅ No configuration  
✅ Built-in web UI  
✅ Same learning outcomes  
✅ Can switch to PostgreSQL later  

---

## 🚀 Quick Start (2 Commands!)

```powershell
cd advodraft-step2-h2
mvn spring-boot:run
```

**That's it!** Database created automatically! 🎉

---

## 🌐 View Your Database (H2 Console)

Open browser: **http://localhost:8080/h2-console**

**Login:**
- JDBC URL: `jdbc:h2:mem:advodraft_dev`
- Username: `sa`
- Password: (leave empty)

Click "Connect" → See your tables and data!

---

## 🧪 Test API (Windows PowerShell)

### Create User
```powershell
$body = @{
    email = "test@example.com"
    fullName = "Test User"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method Post -Body $body -ContentType "application/json"
```

### Get All Users
**Browser:** http://localhost:8080/api/users

**Or PowerShell:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/users"
```

### View in H2 Console
1. Go to H2 console
2. Run: `SELECT * FROM users;`
3. See your data! 🎉

---

## 📚 What You Learn (Identical to PostgreSQL!)

All the same concepts:
- ✅ JPA & Hibernate
- ✅ @Entity, @Id, @Column
- ✅ Spring Data JPA
- ✅ Repository pattern
- ✅ CRUD operations
- ✅ Dependency Injection

**Only difference:** H2 vs PostgreSQL (database choice)  
**Everything else:** IDENTICAL!

---

## 🎯 Study Plan

1. ✅ Run the app (2 mins)
2. ✅ Create users (5 mins)
3. ✅ View in H2 console (5 mins)
4. ✅ Read code comments (30 mins)
5. ✅ Answer interview questions (10 mins)

---

## 🔄 Switch to PostgreSQL Later

When ready:
1. Change `pom.xml` dependency
2. Update `application.yml` config
3. Install PostgreSQL
4. Done! Same code works!

---

## 📖 Full Details

All files have extensive teaching comments explaining:
- What each annotation does
- Why we use it
- How it works
- Interview questions

Read these in order:
1. `application.yml` - Database config
2. `User.java` - Entity annotations
3. `UserRepository.java` - Spring Data magic
4. `UserController.java` - CRUD endpoints

---

## 💡 H2 vs PostgreSQL

| Feature | H2 | PostgreSQL |
|---------|-----|-----------|
| For Learning | ✅ Perfect | ✅ Perfect |
| Installation | ✅ None | ❌ Required |
| Web Console | ✅ Built-in | ❌ Separate tool |
| Data Persistence | ❌ In-memory | ✅ On disk |
| Production | ❌ No | ✅ Yes |

**Conclusion:** Learn with H2, deploy with PostgreSQL!

---

## 🎓 Interview Questions You'll Answer

After this step:
- ✅ What is JPA and Hibernate?
- ✅ How does Spring Data JPA work?
- ✅ What is Dependency Injection?
- ✅ Explain @Entity and @Id
- ✅ Why use Spring Data repositories?
- ✅ What's the difference between H2 and PostgreSQL?

---

## ➡️ Next: Step 3

User Registration with:
- Password hashing
- Input validation
- DTOs
- Service layer

---

**Progress: ████░░░░░░░░░░░░░░░░ 22% (2/9)**

**Ready? Run it and explore! 🚀**
