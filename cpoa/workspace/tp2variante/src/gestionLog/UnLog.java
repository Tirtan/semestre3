package gestionLog;

/*
 *  Il vous est demandé de

    lire ce fichier texte (cf code java ci-dessous) et de le stocker dans une ArrayList <UnLog>. La classe UnLog, que vous devez écrire, contient 3 String : la date, l'heure et l'IP.
    d'afficher le nombre de connexion au site par jour
    d'afficher le nombre de fois où une IP a été utilisée
    d'afficher le nombre de fois où une IP a été utilisée avec affichage trié par IP (la + utilisée en 1er) et par date 

De plus, vous devrez implémenter une classe de test JUnit TraiteLogTest, basée sur un fichier de données réduit countModifTest.txt, qui permettra de valider les principales méthodes de comptage réalisées. 
 * */

public class UnLog
{
	private String date;
	private String heure;
	private String ip;
	
	/* Générer automatiquement par Eclipse */
	public UnLog(String date, String heure, String ip)
	{
		this.date = date;
		this.heure = heure;
		this.ip = ip;
	}

	public String getDate()
	{
		return date;
	}

	public String getHeure()
	{
		return heure;
	}

	public String getIp()
	{
		return ip;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public void setHeure(String heure)
	{
		this.heure = heure;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String toString()
	{
		return "UnLog [date=" + date + ", heure=" + heure + ", ip=" + ip + "]";
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((heure == null) ? 0 : heure.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		UnLog other = (UnLog) obj;
		if (date == null)
		{
			if (other.date != null) return false;
		}
		else if (!date.equals(other.date)) return false;
		if (heure == null)
		{
			if (other.heure != null) return false;
		}
		else if (!heure.equals(other.heure)) return false;
		if (ip == null)
		{
			if (other.ip != null) return false;
		}
		else if (!ip.equals(other.ip)) return false;
		return true;
	}
	
	
	

}
