import java.util.ArrayList;

public class JoueurArtificiel implements Joueur {

    private static final int PROFONDEUR_MAX = 10;

    @Override
    public String getAuteurs() {
        return "Aboubakrine Diallo, Cheikhoul Khadim Diop";
    }

    @Override
    public String getProchaineAction(char[] cases, int pmax, int pmin, int pdrap, int dureeR) {
        long deadline = System.currentTimeMillis() + dureeR - 30;

        Etat etatInitial = new Etat(cases, pmax, pmin, pdrap);
        ArrayList<Integer> actions = genererActions(etatInitial, true);

        int meilleurScore = Integer.MIN_VALUE;
        int meilleurCoup = 0;

        for (int a : actions) {
            Etat succ = etatInitial.appliquer(a, true);
            int score = minimax(succ, PROFONDEUR_MAX - 1, false, Integer.MIN_VALUE, Integer.MAX_VALUE, deadline);
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleurCoup = a;
            }
        }

        if (meilleurCoup < -3) return "-D";
        if (meilleurCoup > 3) return "+D";
        return (meilleurCoup >= 0 ? "+" : "") + meilleurCoup;
    }

    // === Minimax avec élagage ===
    private int minimax(Etat etat, int profondeur, boolean maxJoue, int alpha, int beta, long deadline) {
        if (System.currentTimeMillis() > deadline || profondeur == 0 || etat.estTerminal()) {
            return etat.evaluation();
        }

        ArrayList<Integer> actions = genererActions(etat, maxJoue);
        if (actions.isEmpty()) return etat.evaluation();

        if (maxJoue) {
            int maxEval = Integer.MIN_VALUE;
            for (int a : actions) {
                Etat succ = etat.appliquer(a, true);
                int eval = minimax(succ, profondeur - 1, false, alpha, beta, deadline);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int a : actions) {
                Etat succ = etat.appliquer(a, false);
                int eval = minimax(succ, profondeur - 1, true, alpha, beta, deadline);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            return minEval;
        }
    }

    // === Génère toutes les actions possibles pour un joueur ===
    private ArrayList<Integer> genererActions(Etat e, boolean maxJoue) {
        ArrayList<Integer> actions = new ArrayList<>();
        int pos = maxJoue ? e.pmax : e.pmin;
        for (int d = -9; d <= 9; d++) {
            if (d == 0) continue;
            int cible = pos + d;
            if (cible < 0 || cible >= e.cases.length) continue;
            if (e.cases[cible] == 'x') continue;
            actions.add(d);
        }
        return actions;
    }

    // === Classe interne représentant un état du jeu ===
    class Etat {
        char[] cases;
        int pmax, pmin, pdrap;

        Etat(char[] c, int max, int min, int d) {
            this.cases = c.clone();
            this.pmax = max;
            this.pmin = min;
            this.pdrap = d;
        }

        boolean estTerminal() {
            return pmax == pdrap || pmin == pdrap;
        }

        int evaluation() {
            if (pmax == pdrap) return 10000;
            if (pmin == pdrap) return -10000;

            int distMax = pdrap - pmax;
            int distMin = pdrap - pmin;

            int accesMax = actionsPossibles(pmax);
            int accesMin = actionsPossibles(pmin);
            if (accesMax == 0) return -8000;
            if (accesMin == 0) return 8000;

            int dangerMax = countBurnedAround(pmax);
            int dangerMin = countBurnedAround(pmin);

            int score = 0;
            score += (distMin - distMax) * 15;
            score += 50 * (accesMax - accesMin);
            score += (dangerMin - dangerMax) * 10;

            return score;
        }

        Etat appliquer(int d, boolean maxJoue) {
            int newPmax = pmax, newPmin = pmin;
            char[] nouvCases = cases.clone();
            if (maxJoue) {
                nouvCases[pmax] = 'x';
                newPmax += d;
            } else {
                nouvCases[pmin] = 'x';
                newPmin += d;
            }

            int newDrap = pdrap;
            if (maxJoue && pmax == pdrap && pmax != pmin) {
                newDrap = newPmax;
            } else if (!maxJoue && pmin == pdrap && pmin != pmax) {
                newDrap = newPmin;
            }

            return new Etat(nouvCases, newPmax, newPmin, newDrap);
        }

        int countBurnedAround(int pos) {
            int danger = 0;
            for (int d = -2; d <= 2; d++) {
                int i = pos + d;
                if (i >= 0 && i < cases.length && cases[i] == 'x') {
                    danger++;
                }
            }
            return danger;
        }

        int actionsPossibles(int pos) {
            int nb = 0;
            for (int d = -3; d <= 3; d++) {
                if (d == 0) continue;
                int i = pos + d;
                if (i >= 0 && i < cases.length && cases[i] == ' ') nb++;
            }
            return nb;
        }
    }
}
