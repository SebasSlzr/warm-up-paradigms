import os

def obtener_estadisticas_archivo(ruta_archivo):
    lineas = 0
    palabras = 0
    caracteres = 0

    try:
        with open(ruta_archivo, "r", encoding="utf-8") as f:
            for linea in f:
                lineas += 1
                palabras += len(linea.split())
                caracteres += len(linea)
    except Exception as e:
        print("No se pudo leer el archivo")
    
    return lineas, palabras, caracteres


def analizar_carpeta(ruta_carpeta):
    resultados = []

    for carpeta_actual, subcarpetas, archivos in os.walk(ruta_carpeta):
        for archivo in archivos:
            if archivo.endswith(".txt"):
                ruta_completa = os.path.join(carpeta_actual, archivo)
                lineas, palabras, caracteres = obtener_estadisticas_archivo(ruta_completa)
                resultados.append((archivo, lineas, palabras, caracteres))

    return resultados


def imprimir_resultados(resultados):
    if not resultados:
        print("No se encontraron archivos de texto en la carpeta.")
        return
    
    total_lineas = sum(r[1] for r in resultados)
    total_palabras = sum(r[2] for r in resultados)
    total_caracteres = sum(r[3] for r in resultados)

    print(f"{'Archivo':<30} {'Lineas':<10} {'Palabras':<10} {'Caracteres':<10}")
    print("-" * 60)

    for archivo, lineas, palabras, caracteres in resultados:
        print(f"{archivo:<30} {lineas:<10} {palabras:<10} {caracteres:<10}")

    print("-" * 60)
    print(f"{'TOTAL':<30} {total_lineas:<10} {total_palabras:<10} {total_caracteres:<10}")


if __name__ == "__main__":
    ruta = input("Ingrese la ruta de la carpeta a analizar: ")
    if os.path.isdir(ruta):
        resultados = analizar_carpeta(ruta)
        imprimir_resultados(resultados)
    else:
        print("La ruta ingresada no es valida.")
