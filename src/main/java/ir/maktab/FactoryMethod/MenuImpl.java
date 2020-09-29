package ir.maktab.FactoryMethod;

public abstract class MenuImpl {
    protected int option;

    public abstract void display();

    public abstract void menuHandler();

    public abstract boolean checkChoice();

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
