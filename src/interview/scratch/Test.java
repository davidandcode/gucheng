package interview.scratch;

class Test {
    public int x =1;
    public int y =3;
}

class TestChild extends Test{
    {
        this.x = 2;
        this.y = 4;
    }
}
