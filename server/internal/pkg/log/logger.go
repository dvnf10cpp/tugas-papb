package log

import (
	log "github.com/sirupsen/logrus"
)

type LogInfo map[string]interface{}

func getLogger(fields LogInfo) (*log.Logger, map[string]interface{}) {
	var converted map[string]interface{} = fields 
	log := log.New()

	return log, converted
}

func Info(fields LogInfo, info string) {
	log, conv := getLogger(fields)
	
	log.WithFields(conv).Info(info)
}

func Error(fields LogInfo, info string) {
	log, conv := getLogger(fields)
	
	log.WithFields(conv).Error(info)
}

func Fatal(fields LogInfo, info string) {
	log, conv := getLogger(fields)
	
	log.WithFields(conv).Fatal(info)
}