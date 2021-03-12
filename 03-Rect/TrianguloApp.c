typedef struct {

    int base, altura;
    int posx, posy;

} Triangulo;


int main(int argc, char const *argv[])
{
    Triangulo t1 = {10, 4, 1, 1};
    print(&t1);
    drag(&t1, 3, 4);
    print(&t1);
    return 0;
}

void print(Triangulo* t) {
    printf("Esse triangulo tem base %d e altura %d e esta na posicao (%d,%d)\n", t->base, t->altura, t->posx, t->posy);
    printf("Area do triangulo: %d\n", calculaArea(t));
}

int calculaArea(Triangulo* t) {
    return (t->base * t->altura) /2;
}

void drag(Triangulo* t, int dx, int dy) {
    t->posx += dx; t->posy += dy;
}
