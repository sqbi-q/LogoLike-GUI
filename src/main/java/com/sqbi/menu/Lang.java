package com.sqbi.menu;

public class Lang {
	private enum LANG {
		File(new String[] {"File", "Plik"}),
		New(new String[] {"New", "Nowy"}),
		Save(new String[] {"Save", "Zapisz"}),
		Create_proj_directory(new String[] {"Create project directory", "Utwórz folder projektu"}),
		is_corr_path(new String[] {"Is this path '%s' correct?", "Czy ta ścieżka '%s' jest poprawna?"}),
		couldnt_create_dir(new String[] {"Could not create directory", "Nie można utworzyć folderu"}),
		couldnt_open_project(new String[] {"Could not open project directory", "Nie można otworzyć folderu projektu"}),
		Open(new String[] {"Open", "Otwórz"}),
		Export(new String[] {"Export", "Eksportuj"}),
		Import(new String[] {"Import", "Importuj"}),
		Run(new String[] {"Run", "Uruchom"}),
		Build(new String[] {"Build", "Zbuduj"}),
		About(new String[] {"About", "O Programie"}),
		Help(new String[] {"Help", "Pomoc"}),
		Documentation(new String[] {"Documentation", "Dokumentacja"});
		
		String[] names;
		
		LANG(String... s) {
			this.names = s;
		}
		public String[] getNames() {
			return this.names;
		}
		
	};
	private int LANG_NR;
	
	public void setLang(int lang_nr) {
		this.LANG_NR = lang_nr;
	}
	
	public String getName(String name) {
		return LANG.valueOf(name).getNames()[LANG_NR];
	}
}
