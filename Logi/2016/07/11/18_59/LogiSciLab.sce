//
// Plik wczytujacy dane z pliku LogiSciLabbDane 
// data zbierania logow: 18_59 11/07/2016

// 
// Opis zmiennych:
// AccGyroRaw_ACCX - wektor zaiwrajacy parametru :ACCX z pliku: AccGyroRaw_log.txt
// AccGyroRaw_ACCX_Size - stala zawierajaca rozmiar powyzszego wektora
// AccGyroRaw_ACCY - wektor zaiwrajacy parametru :ACCY z pliku: AccGyroRaw_log.txt
// AccGyroRaw_ACCY_Size - stala zawierajaca rozmiar powyzszego wektora
// AccGyroRaw_ACCZ - wektor zaiwrajacy parametru :ACCZ z pliku: AccGyroRaw_log.txt
// AccGyroRaw_ACCZ_Size - stala zawierajaca rozmiar powyzszego wektora
// AccGyroRaw_GYROX - wektor zaiwrajacy parametru :GYROX z pliku: AccGyroRaw_log.txt
// AccGyroRaw_GYROX_Size - stala zawierajaca rozmiar powyzszego wektora
// AccGyroRaw_GYROY - wektor zaiwrajacy parametru :GYROY z pliku: AccGyroRaw_log.txt
// AccGyroRaw_GYROY_Size - stala zawierajaca rozmiar powyzszego wektora
// AccGyroRaw_GYROZ - wektor zaiwrajacy parametru :GYROZ z pliku: AccGyroRaw_log.txt
// AccGyroRaw_GYROZ_Size - stala zawierajaca rozmiar powyzszego wektora
// Barometr_T - wektor zaiwrajacy parametru :T z pliku: Barometr_log.txt
// Barometr_T_Size - stala zawierajaca rozmiar powyzszego wektora
// Barometr_P - wektor zaiwrajacy parametru :P z pliku: Barometr_log.txt
// Barometr_P_Size - stala zawierajaca rozmiar powyzszego wektora
// Barometr_W - wektor zaiwrajacy parametru :W z pliku: Barometr_log.txt
// Barometr_W_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_SkladowaH - wektor zaiwrajacy parametru :SkladowaH z pliku: Silnik1_log.txt
// Silnik1_SkladowaH_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_RegulatorAlfa - wektor zaiwrajacy parametru :RegulatorAlfa z pliku: Silnik1_log.txt
// Silnik1_RegulatorAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_RegulatorBeta - wektor zaiwrajacy parametru :RegulatorBeta z pliku: Silnik1_log.txt
// Silnik1_RegulatorBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_UchybAlfa - wektor zaiwrajacy parametru :UchybAlfa z pliku: Silnik1_log.txt
// Silnik1_UchybAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_UchybBeta - wektor zaiwrajacy parametru :UchybBeta z pliku: Silnik1_log.txt
// Silnik1_UchybBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_PWM - wektor zaiwrajacy parametru :PWM z pliku: Silnik1_log.txt
// Silnik1_PWM_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_RegAlfaRate - wektor zaiwrajacy parametru :RegAlfaRate z pliku: Silnik1_log.txt
// Silnik1_RegAlfaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik1_RegBetaRate - wektor zaiwrajacy parametru :RegBetaRate z pliku: Silnik1_log.txt
// Silnik1_RegBetaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_SkladowaH - wektor zaiwrajacy parametru :SkladowaH z pliku: Silnik3_log.txt
// Silnik3_SkladowaH_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_RegulatorAlfa - wektor zaiwrajacy parametru :RegulatorAlfa z pliku: Silnik3_log.txt
// Silnik3_RegulatorAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_RegulatorBeta - wektor zaiwrajacy parametru :RegulatorBeta z pliku: Silnik3_log.txt
// Silnik3_RegulatorBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_UchybAlfa - wektor zaiwrajacy parametru :UchybAlfa z pliku: Silnik3_log.txt
// Silnik3_UchybAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_UchybBeta - wektor zaiwrajacy parametru :UchybBeta z pliku: Silnik3_log.txt
// Silnik3_UchybBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_PWM - wektor zaiwrajacy parametru :PWM z pliku: Silnik3_log.txt
// Silnik3_PWM_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_RegAlfaRate - wektor zaiwrajacy parametru :RegAlfaRate z pliku: Silnik3_log.txt
// Silnik3_RegAlfaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik3_RegBetaRate - wektor zaiwrajacy parametru :RegBetaRate z pliku: Silnik3_log.txt
// Silnik3_RegBetaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_SkladowaH - wektor zaiwrajacy parametru :SkladowaH z pliku: Silnik4_log.txt
// Silnik4_SkladowaH_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_RegulatorAlfa - wektor zaiwrajacy parametru :RegulatorAlfa z pliku: Silnik4_log.txt
// Silnik4_RegulatorAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_RegulatorBeta - wektor zaiwrajacy parametru :RegulatorBeta z pliku: Silnik4_log.txt
// Silnik4_RegulatorBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_UchybAlfa - wektor zaiwrajacy parametru :UchybAlfa z pliku: Silnik4_log.txt
// Silnik4_UchybAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_UchybBeta - wektor zaiwrajacy parametru :UchybBeta z pliku: Silnik4_log.txt
// Silnik4_UchybBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_PWM - wektor zaiwrajacy parametru :PWM z pliku: Silnik4_log.txt
// Silnik4_PWM_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_RegAlfaRate - wektor zaiwrajacy parametru :RegAlfaRate z pliku: Silnik4_log.txt
// Silnik4_RegAlfaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik4_RegBetaRate - wektor zaiwrajacy parametru :RegBetaRate z pliku: Silnik4_log.txt
// Silnik4_RegBetaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Orientacja_Alfa - wektor zaiwrajacy parametru :Alfa z pliku: Orientacja_log.txt
// Orientacja_Alfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Orientacja_Beta - wektor zaiwrajacy parametru :Beta z pliku: Orientacja_log.txt
// Orientacja_Beta_Size - stala zawierajaca rozmiar powyzszego wektora
// Orientacja_Gamma - wektor zaiwrajacy parametru :Gamma z pliku: Orientacja_log.txt
// Orientacja_Gamma_Size - stala zawierajaca rozmiar powyzszego wektora
// Orientacja_AlfaRate - wektor zaiwrajacy parametru :AlfaRate z pliku: Orientacja_log.txt
// Orientacja_AlfaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Orientacja_BetaRate - wektor zaiwrajacy parametru :BetaRate z pliku: Orientacja_log.txt
// Orientacja_BetaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Orientacja_GammaRate - wektor zaiwrajacy parametru :GammaRate z pliku: Orientacja_log.txt
// Orientacja_GammaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Akcelerometr_X - wektor zaiwrajacy parametru :X z pliku: Akcelerometr_log.txt
// Akcelerometr_X_Size - stala zawierajaca rozmiar powyzszego wektora
// Akcelerometr_Y - wektor zaiwrajacy parametru :Y z pliku: Akcelerometr_log.txt
// Akcelerometr_Y_Size - stala zawierajaca rozmiar powyzszego wektora
// Akcelerometr_Z - wektor zaiwrajacy parametru :Z z pliku: Akcelerometr_log.txt
// Akcelerometr_Z_Size - stala zawierajaca rozmiar powyzszego wektora
// Akcelerometr_Dlugoscwektora - wektor zaiwrajacy parametru :Dlugoscwektora z pliku: Akcelerometr_log.txt
// Akcelerometr_Dlugoscwektora_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_SkladowaH - wektor zaiwrajacy parametru :SkladowaH z pliku: Silnik2_log.txt
// Silnik2_SkladowaH_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_RegulatorAlfa - wektor zaiwrajacy parametru :RegulatorAlfa z pliku: Silnik2_log.txt
// Silnik2_RegulatorAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_RegulatorBeta - wektor zaiwrajacy parametru :RegulatorBeta z pliku: Silnik2_log.txt
// Silnik2_RegulatorBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_UchybAlfa - wektor zaiwrajacy parametru :UchybAlfa z pliku: Silnik2_log.txt
// Silnik2_UchybAlfa_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_UchybBeta - wektor zaiwrajacy parametru :UchybBeta z pliku: Silnik2_log.txt
// Silnik2_UchybBeta_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_PWM - wektor zaiwrajacy parametru :PWM z pliku: Silnik2_log.txt
// Silnik2_PWM_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_RegAlfaRate - wektor zaiwrajacy parametru :RegAlfaRate z pliku: Silnik2_log.txt
// Silnik2_RegAlfaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Silnik2_RegBetaRate - wektor zaiwrajacy parametru :RegBetaRate z pliku: Silnik2_log.txt
// Silnik2_RegBetaRate_Size - stala zawierajaca rozmiar powyzszego wektora
// Magnetometr_X - wektor zaiwrajacy parametru :X z pliku: Magnetometr_log.txt
// Magnetometr_X_Size - stala zawierajaca rozmiar powyzszego wektora
// Magnetometr_Y - wektor zaiwrajacy parametru :Y z pliku: Magnetometr_log.txt
// Magnetometr_Y_Size - stala zawierajaca rozmiar powyzszego wektora
// Magnetometr_Z - wektor zaiwrajacy parametru :Z z pliku: Magnetometr_log.txt
// Magnetometr_Z_Size - stala zawierajaca rozmiar powyzszego wektora


// Opcja czyszczaca workspace mozna zakomentowac 
 clear all;

 //
 // Wczytanie danych z pliku z logami
 exec('LogiSciLabDane.sce');

// 
// Wlasne operacje ponizej:

