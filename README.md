# ChatCurso

Los archivos java que nos interesan están en la siguiente ruta
- ChatCurso/app/src/main/java/com/example/chatcurso/

Modificamos dos archivos gradle

El primero se encuentra en 
- ChatCurso/build.gradle

Y sólo agregamos la línea
- classpath 'com.google.gms:google-services:4.3.4'

El segundo archivo gradle que modificamos se encuentra en
- ChatCurso/app/build.gradle

En donde agregamos las dependencias de firebase, material design, recycler view y circle image view

Las librerías que utilizamos las tomamos de 

- https://github.com/hdodenhof/CircleImageView (circle image view)
- https://firebase.google.com/docs/auth?authuser=0 (autenticación con Firebase)
- https://firebase.google.com/docs/firestore?authuser=0 (base de datos Firestore)
- https://material.io/develop/android/docs/getting-started (Material Desgin)
