package domaine;

/*brief: La classe emplacement sert à représenter la position d'une pièce dans le plan.
         Les coordonnées x,y, z forment un des 8 coin de l'objet 3D.
         La classe Emplacement représente le coin supérieur gauche, si on regarde la pièce du dessus,
         et se peut importe l'orientation de la pièce car les poteaux, solives, poutre et
         plis sont toujours orientés de la même façon en dépit de la vue utilisée.

         Le côté de la maison sur lequel est ancré le patio est situé en haut à gauche du plan. La façade commence à
         l'origine du plan et s'étale sur l'axe des y de tel que x vaut toujours 0 pour tout point de la façade
          de la maison
 */

public class Emplacement {
    private double x;
    private double y;
    private double z;

    Emplacement(){
        x=y=z=0;
    }

    Emplacement(double in_x, double in_y, double in_z){
        x= in_x;
        y= in_y;
        z= in_z;
    }

    public void setX(double in_x) {
        x = in_x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double in_y) {
        y = in_y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double in_z) {
        z = in_z;
    }

    public void setXYZ(double inX, double inY, double inZ){
    x = inX;
    y = inY;
    z = inZ;
    }
}
