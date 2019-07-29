m = 100000000
m1 = 10000
b = 31415821
start_seed = 3794612
range_p = 20
range_a = 10
range_b = 15
seed = 0

def Mult(p, q):
    p1 = p // m1
    p0 = p % m1
    q1 = q // m1
    q0 = q % m1
    return (((p0 * q1 + p1 * q0) % m1) * m1 + p0 * q0) % m

def Randomint(rang):
    global seed
    seed = (Mult(seed, b) + 1) % m
    return (((seed // m1) * rang) // m1) + 1


for n in [10, 20, 50, 100, 200, 500, 1000]:
    for k in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
        seed = start_seed + n + k
        arquivo = open("sch"+str(n)+"k"+str(k)+".txt", "w+");
        arquivo.write(str(n)+"    0    0\n")
        for i in xrange(n):
            arquivo.write(str(Randomint(range_p)) + "    " + str(Randomint(range_a)) + "    " + str(Randomint(range_b))+"\n")
            
        arquivo.close();
