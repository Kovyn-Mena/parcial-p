# Plantilla P2 — CRUD completo por capas (Java + IntelliJ)

Plantilla lista para la actividad en clase de **Programación 2**: un CRUD completo de consola
con la arquitectura de 4 capas de la diapositiva del profe.

| Capa (diapositiva) | Clases en este proyecto |
|---|---|
| **Entidades** | `entities/Curso`, `entities/EstadoCurso` (enum) |
| **Persistencia** | `repositories/CursoRepository` (interfaz), `repositories/InMemoryCursoRepository` (memoria), `repositories/FileCursoRepository` (archivo / serialización) |
| **Lógica** | `services/CursoService` (validaciones y reglas de negocio) |
| **Presentación** | `presentation/ConsoleCursoView` (menú por consola) + `Main` (ensambla las capas) |

El dominio de ejemplo es **Curso**, con un campo **enum** (`EstadoCurso`) integrado al CRUD:
se valida en la capa de lógica y se elige por menú numerado en la consola.

---

## Requisitos

- **JDK 17 o superior** (el código usa solo Java básico, compatible con cualquier JDK moderno).
- **IntelliJ IDEA** (Community o Ultimate).

## Cómo abrir en IntelliJ

1. `File → Open...` y seleccionar la carpeta **`PlantillaP2`** (la que contiene `src/`).
2. Si IntelliJ avisa que falta el SDK: `Project Structure (Ctrl+Alt+Shift+S) → Project → SDK`
   y elegir cualquier **JDK 17+** instalado. (En este PC está el SDK llamado `26`.)
3. Listo. Ejecutar con el botón ▶ sobre `src/Main.java`.

> Si el proyecto se clona en otra PC donde no existe el SDK `26`, IntelliJ solo pedirá
> reasignar el SDK una vez; el código compila igual desde JDK 17.

## Cómo ejecutar desde la terminal

**Linux / macOS:**

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

**Windows (CMD):**

```cmd
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d out @sources.txt
java -cp out Main
```

> En Windows, `-encoding UTF-8` evita errores con los acentos de los mensajes en español.

## Qué hace el CRUD

Menú de consola:

```
1. Registrar curso
2. Listar cursos
3. Buscar curso por id
4. Actualizar curso
5. Eliminar curso
0. Salir
```

- **Create**: valida campos (nombre ≥ 3 caracteres, docente obligatorio, créditos entre 1 y 20,
  precio no negativo, estado obligatorio), verifica que no exista otro curso con el mismo nombre
  y genera el id.
- **Read**: listar todos y buscar por id (`Optional`).
- **Update**: valida id existente + mismas reglas del create.
- **Delete**: elimina por id.
- **Enum**: `EstadoCurso` (`ABIERTO`, `EN_CURSO`, `FINALIZADO`, `CANCELADO`) se elige por
  número en consola y se valida en la capa de lógica.

## Persistencia: memoria o archivo

Por defecto el `Main` usa **memoria** (`InMemoryCursoRepository`).
Para guardar en archivo (serialización), en `Main.java` comente/descomente:

```java
CursoRepository repository = new InMemoryCursoRepository();
// CursoRepository repository = new FileCursoRepository("cursos.dat");
```

El archivo `cursos.dat` se crea solo en la carpeta del proyecto. `*.dat` está en `.gitignore`.

---

## Checklist de adaptación en clase (dominio nuevo en ~5 minutos)

1. **Renombrar la entidad**: abrir `Curso.java` → `Shift+F6` (Rename) → nuevo nombre
   (marcar *Search in comments and strings* ayuda, pero revise los textos a mano).
2. **Reemplazo global de textos**: `Ctrl+Shift+R` (Replace in Path) sobre `src/`:
   - `Curso` → `NuevoNombre` (clases, tipos, mensajes)
   - `curso` → `nuevoNombre` (variables)
   - `CURSOS` → `NUEVOS` (títulos del menú)
3. **Campos**: en la entidad, ajustar atributos; generar getters/setters/constructor con
   `Alt+Insert`; ajustar `toString()`.
4. **Validaciones**: en `CursoService.validateFields()` poner las reglas del dominio y en
   `validateNombreRepetido()` las reglas de negocio (duplicados, límites, etc.).
5. **Vista**: solo cambian `readEntity()` (agregar/quitar `readValue/readInt/readDouble`) y
   `showEntity()` (imprimir los campos). El menú y el resto de la vista no se tocan.
6. **Correr y probar**: registrar, listar, buscar, actualizar, eliminar.

### Agregar otro campo enum

1. Crear el enum en `entities/` con el mismo patrón de `EstadoCurso.java`.
2. Agregar el campo + getter/setter en la entidad.
3. Copiar el método `readEstado()` de la vista y adaptarlo al nuevo enum.
4. Llamarlo en `readEntity()` y mostrarlo en `showEntity()`.
5. Validar en `CursoService` que no venga nulo.

---

## Subir a GitHub (desde este PC)

```bash
git remote add origin https://github.com/TU_USUARIO/TU_REPO.git
git branch -M main
git push -u origin main
```

## Clonar en la PC de la universidad (Windows)

```bash
git clone https://github.com/TU_USUARIO/TU_REPO.git
```

1. Abrir la carpeta clonada en IntelliJ (`File → Open`).
2. Si pide SDK: elegir el JDK que tenga la PC (17+).
3. Ejecutar `Main`. No se necesita nada más.

> Alternativa sin git: botón verde **Code → Download ZIP** en GitHub.

---

### Notas

- Los mensajes de consola están en español, siguiendo el estilo del código del profe
  (`create`, `listAll`, `findOne`, `generateId`).
- El proyecto no usa Maven/Gradle ni librerías externas: es un módulo Java simple,
  igual que los proyectos vistos en clase.
