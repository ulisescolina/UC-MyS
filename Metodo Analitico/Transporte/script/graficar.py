from matplotlib import pyplot as plt
from os.path import expanduser
home = expanduser("~")

with open(home+'/marcas_de_clase', 'r') as mc:
    marcaClase = mc.read().split(',')
    # Seteo la variable x de ambos graficos con las marcas de clase
    x1 = list(map(float, marcaClase))
    x2 = list(map(float, marcaClase))

# Abro el archivo que representa las componentes en Y para una  grafica teorica de la distribucion
with open(home+'/eje_y_t', 'r') as y_t:
    yt = y_t.read().split(',')
    yt = list(map(float, yt))

# Abro el archivo que representa las componentes en X para una  grafica observada de la distribucion
with open(home+'/eje_y_o', 'r') as y_o:
    yo = y_o.read().split(',')
    yo = list(map(float, yo))

plt.plot(x1, yt, label='Frecuencia Teorica')
plt.plot(x2, yo, label='Frecuencia Observada')
# Definimos que se imprima una leyenda indicando a que grafico
# corresponde cada color
plt.legend()
# Decimos que se muestre el grafico que graficamos
plt.show()
