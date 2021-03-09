package ru.job4j;

/**
 * Object of this class takes up in memory 1024 bytes:
 * 1008 bytes (8 * 126) of primitive double and long values + 16 bytes (12 header of object + 4 offset for multiplicity by 8))
 * for hotspot x64.
 * В данном случае получается 12 байт - это пустой объект + 1008 байт + 4 байта для выравнивания кратности 8.
 */

public class User {
    double q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m;
    double q1,w1,e1,r1,t1,y1,u1,i1,o1,p1,a1,s1,d1,f1,g1,h1,j1,k1,l1,z1,x1,c1,v1,b1,n1,m1;
    double q2,w2,e2,r2,t2,y2,u2,i2,o2,p2,a2,s2,d2,f2,g2,h2,j2,k2,l2,z2,x2,c2;
    long qq,ww,ee,rr,tt,yy,uu,ii,oo,pp,aa,ss,dd,ff,gg,hh,jj,kk,ll,zz,xx,cc,vv,bb,nn,mm;
    long qq1,ww1,ee1,rr1,tt1,yy1,uu1,ii1,oo1,pp1,aa1,ss1,dd1,ff1,gg1,hh1,jj1,kk1,ll1,zz1,xx1,cc1,vv1,bb1,nn1,mm1;

    private static final long KB = 1024;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();
    private static int deleted = 0;

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / KB);
        System.out.printf("Total: %d%n", totalMemory / KB);
        System.out.printf("Max: %d%n", maxMemory / KB);
    }

    public User(double q){
        this.q = q;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Removed by GC " + q + " user" + ". Deleted = " + deleted++);
    }

    public static void main(String[] args) throws InterruptedException {
        info();
        //780 and 800
        for (int i = 0; i < 780; i++) {
            new User(i);
        }
        info();
    }
}
