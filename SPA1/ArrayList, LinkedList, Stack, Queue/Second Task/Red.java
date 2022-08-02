/**
 * Tip podataka red opsluzivanja, koji omogucava skladistenje podataka u skladu
 * sa principom "prvi unutra, prvi napolje".
 * 
 * <p>
 * Ova klasa koristi niz za skladistenje podataka, te je samim tim ogranicena i
 * moguce je da metod za dodavanje elemenata baci izuzetak u slucaju da vise
 * nema mesta.
 * </p>
 *
 * @version v1.0.0
 *
 * @param <T>
 *            Tip podataka koji ce se cuvati u konkretnoj instanci reda
 *            opsluzivanja.
 */
public class Red<T> {
	/**
	 * Separator vrednosti u {@code toString} metodu: {@value} .
	 */
	public static final String SEPARATOR = ", ";

	private int prvi, poslednji;
	private T[] elementi;

	/**
	 * Velicina redova za koje nije prosledjen parametar o velicini. Konkretna
	 * vrednost je {@value} .
	 */
	public static final int PODRAZUMEVANA_VELICINA = 100;

	/**
	 * Kreira novi red opsluzivanja podrazumevane velicine
	 * {@value #PODRAZUMEVANA_VELICINA}.
	 */
	public Red() {
		this(PODRAZUMEVANA_VELICINA);
	}

	/**
	 * Kreira novi red opsluzivanja zadate velicine.
	 * 
	 * @param n
	 *            broj elemenata koji red opsluzivanja moze maksimalno da primi.
	 */
	// pozeljno koristiti Suppress da kompajliranje ne prijavljuje upozorenja
	@SuppressWarnings("unchecked")
	public Red(int n) {
		prvi = -1;
		poslednji = -1;
		elementi = (T[]) (new Object[n]);
	}

	/**
	 * Pomocni metod za zadrzavanje indeksa niza u opsegu. Metod uvek vraca
	 * indeks sledeceg elementa u odnosu na onaj koji je prosledjen. Sledeci
	 * element je za jedno mesto vise od trenutnog, osim za poslednji kome je
	 * sledeci prvi.
	 * 
	 * @param i
	 *            indeks elementa za koji se trazi sledeci
	 * @return indeks elementa koji je posle onog koji je prosledjen.
	 */
	private int dodajJedan(int i) {
		return (i + 1) % elementi.length;
	}

	/**
	 * Vraca da li je red prazan.
	 * 
	 * @return da li je red prazan
	 */
	public boolean jePrazan() {
		return poslednji == -1;
	}

	/**
	 * Vraca da li je red pun.
	 * 
	 * @return da li je red pun
	 */
	public boolean jePun() {
		return dodajJedan(poslednji) == prvi;
	}

	/**
	 * Vraca vrednost elementa na pocetku reda opsluzivanja. Ukoliko je red
	 * prazan baca se izuzetak.
	 * 
	 * @return vrednost elementa na pocetku reda opsluzivanja
	 */
	public T prvi() {
		if (jePrazan())
			throw new IllegalStateException("Red je prazan");
		else
			return elementi[prvi];
	}

	/**
	 * Izbacuje element sa pocetka reda opsluzivanja i vraca ga. Ukoliko je red
	 * prazan baca se izuzetak.
	 * 
	 * @return vrednost elementa koji je bio na pocetku reda opsluzivanja
	 */
	public T izbaciPrvi() {
		if (jePrazan()) {
			throw new IllegalStateException("Red je prazan");
		} else {
			T el = elementi[prvi];
			if (prvi == poslednji) {
				prvi = -1;
				poslednji = -1;
			} else
				prvi = dodajJedan(prvi);
			return el;
		}
	}

	/**
	 * Ubacuje prosledjeni element na kraj reda opsluzivanja. Ukoliko je red vec
	 * pun baca se izuzetak.
	 * 
	 * @param x
	 *            element koji ce biti ubacen na kraj reda
	 */
	public void naKraj(T x) {
		if (jePun())
			throw new IllegalStateException("Red je pun");
		else {
			if (jePrazan()) {
				prvi = 0;
				poslednji = 0;
			} else
				poslednji = dodajJedan(poslednji);
			elementi[poslednji] = x;
		}
	}

	/**
	 * Vraca String reprezentaciju ovog Reda. Reprezentacija ce sadrzati
	 * najvise 4 elementa iz reda, tacnije najvise prva dva i poslednja dva,
	 * razdvojenih sa {@value #SEPARATOR}, a ukoliko ima vise od 4 elementa bice
	 * dodato i "..." izmedju prvih i poslednjih elemenata.
	 */
	public String toString() {
		String rez = "Red:";

		if (jePrazan()) {
			rez += "prazan";
		} else {
			rez += elementi[prvi];
			if (prvi != poslednji) {
				int drugi = dodajJedan(prvi);
				rez += SEPARATOR + elementi[drugi];
				if (drugi != poslednji) {

					int predposlednji = poslednji - 1;
					if (poslednji == 0) {
						predposlednji = elementi.length - 1;
					}
					
					if (predposlednji != drugi) {
						if (dodajJedan(drugi) != predposlednji) {
							rez += SEPARATOR + "...";
						}
						rez += SEPARATOR + elementi[predposlednji];
					}
					rez += SEPARATOR + elementi[poslednji];
				}
			}
		}
		return rez;
	}
}
