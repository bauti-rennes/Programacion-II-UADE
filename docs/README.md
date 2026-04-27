# Programación II — UADE

Proyecto educativo en Java que implementa las principales estructuras de datos de la materia. Cada módulo tiene una interfaz, dos implementaciones (estática y dinámica) y un ejercicio interactivo.

---

## Criterio de UX / UI

Todos los ejercicios siguen el mismo modelo de interacción:

- El programa arranca en un **menú principal** numerado. El usuario elige un número para entrar a un ejercicio.
- Dentro de cada ejercicio hay un **submenú** con las operaciones disponibles (agregar, eliminar, ver, etc.).
- Cada operación guía al usuario paso a paso: primero pide los datos, luego confirma la acción y muestra el resultado.
- Para salir de cualquier pantalla siempre hay una opción numerada visible (generalmente la última del menú).
- Los errores de entrada (texto donde se esperaba número, opción fuera de rango) se manejan en el momento sin cortar el flujo: se muestra un mensaje y se repite la pregunta.

---

## Menú principal

Al iniciar el programa aparece este menú. Cada opción instancia el ejercicio correspondiente y lo ejecuta:

```
0 - Terminar el programa
1 - Ejercicio de prueba
2 - Ejercicio de listas
3 - Ejercicio de pilas
4 - Ejercicio de colas
5 - Ejercicio de conjuntos
6 - Ejercicio de colas de prioridad
7 - Ejercicio de diccionarios
```

La opción `1` es un ejercicio mínimo de prueba que solo regresa al menú principal. Las opciones `2` a `7` abren cada estructura de datos.

---

## Ejercicios

### Lista

**Qué hace:** permite guardar una colección de elementos con posición. Se puede agregar en cualquier lugar de la lista, eliminar por índice o por valor, y consultar qué hay en cada posición.

**Clases:**
- **Estática** (`SimpleArrayList`): usa un array que duplica su tamaño cuando se llena.
- **Dinámica** (`SimpleLinkedList`): usa nodos doblemente enlazados.

---

### Pila (Stack)

**Qué hace:** estructura tipo LIFO (último en entrar, primero en salir). Se apilan elementos con `push` y se sacan con `pop`. `peek` muestra el tope sin sacarlo.

**Clases:**
- **Estática** (`SimpleArrayStack`): array con puntero al tope.
- **Dinámica** (`SimpleLinkedStack`): nodos enlazados, referencia al último nodo.

---

### Cola (Queue)

**Qué hace:** estructura tipo FIFO (primero en entrar, primero en salir). Se encolan elementos por un extremo y se desencolan por el otro.

**Clases:**
- **Estática** (`SimpleArrayQueue`): array donde se encola al final y se desencola del inicio.
- **Dinámica** (`SimpleLinkedQueue`): nodos enlazados con referencias a ambos extremos para operar en O(1).

---

### Conjunto (Set)

**Qué hace:** colección que no permite duplicados. Soporta las operaciones clásicas de teoría de conjuntos: unión, intersección y diferencia. El ejercicio trabaja con dos conjuntos al mismo tiempo.

**Clases:**
- **Estática** (`SimpleArraySet`): array con verificación de duplicados antes de insertar.
- **Dinámica** (`SimpleLinkedSet`): nodos enlazados con la misma verificación.

---

### Cola de Prioridad

**Qué hace:** cola donde cada elemento tiene una prioridad numérica. El elemento con número más bajo sale primero (1 = crítico, 4 = bajo). El ejercicio simula un sistema de reportes: se redactan con un nivel de urgencia y se atienden en orden de prioridad. Al visualizar un reporte se puede marcarlo como resuelto o postergarlo.

**Clases:**
- **Estática** (`SimpleArrayPriorityQueue`): dos arrays paralelos (elementos y prioridades), inserta en posición ordenada.
- **Dinámica** (`SimpledLinkedPriorityQueue`): nodos con campo `priority`, inserta buscando la posición desde el final.

---

### Diccionario

**Qué hace:** estructura clave–valor. Permite guardar, buscar y eliminar entradas por clave. No admite claves repetidas. El ejercicio es un sistema de login con registro de usuarios: valida que el nombre no exista al registrarse y bloquea la cuenta después de tres intentos fallidos de contraseña.

**Clases:**
- **Estática** (`SimpleArrayDictionary`): dos arrays paralelos (claves y valores).
- **Dinámica** (`SimpleLinkedDictionary`): nodos enlazados con par clave–valor.
